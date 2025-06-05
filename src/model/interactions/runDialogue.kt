package model.interactions

import model.data.DialogueNode
import model.data.NPCs
import model.util.clearConsole

fun runDialogue(NPCs: NPCs) {
    val stack = ArrayDeque<DialogueNode>()
    var current: DialogueNode? = NPCs.rootDialogue
    val theirName = NPCs.name

    var wait1000:Long = 1000
    var wait1500:Long = 1500
    var wait250 :Long  = 250
    if (false) {
        wait1000 = 0
        wait1500 = 0
        wait250 = 0
    }
    clearConsole()
    println("$theirName: ${current!!.response}")

    while (current != null) {

        if (current.response != NPCs.rootDialogue.response) {
            println("$theirName: ${current.response}")
        }
        println("")


        if (current.triggerEvent == true) {
            println("$theirName: ${NPCs.goodbye}")
            println("")
            Thread.sleep(wait1000)
            NPCs.event?.npcActivateEvent()
            break
        }

        Thread.sleep(wait1500)

        if (current.next.isEmpty()) {
            if (current.goBackOnEnd && stack.isNotEmpty()) {
                current = stack.removeLast()
                continue
            } else {
                println("$theirName: ${NPCs.goodbye}")
                println("")
                break
            }
        }

        println("\nChoose your response:")
        current.next.forEachIndexed { index, option ->
            println("[${index +1}] :  ${option.prompt}")
        }
        println("")
        print("> ")
        val input = readLine()?.toIntOrNull()?.minus(1)
        val readline: String = readLine().toString()
        println("readline : $readline")

        if (readline == "skip") {
            println("$theirName: ${NPCs.goodbye}")
            println("")
            NPCs.event?.npcActivateEvent()
            break
        }

        if (input != null && input in current.next.indices) {
            stack.addLast(current)
            clearConsole()
            println("You: ${current.next[input].prompt}")
            Thread.sleep(wait250)
            current = current.next[input]
            println("")
            println("")
        } else {
            println("$theirName: What did you just say to me?!")
        }
    }
}