package model.interactions

import model.data.DialogueNode
import model.data.Entities

fun runDialogue(entities: Entities) {
    val stack = ArrayDeque<DialogueNode>()
    var current: DialogueNode? = entities.rootDialogue
    val theirName = entities.name

    println("$theirName: ${current!!.response}")

    while (current != null) {
        if (current.response != entities.rootDialogue.response) {
            println("$theirName: ${current.response}")
        }
        println("")
        println("")
        println("")

        if (current.triggerEvent == true) {
            println("$theirName: ${entities.goodbye}")
            println("")
            Thread.sleep(1000)
            entities.event?.entityActivateEvent()
            break
        }

        Thread.sleep(1500)

        if (current.next.isEmpty()) {
            if (current.goBackOnEnd && stack.isNotEmpty()) {
                current = stack.removeLast()
                continue
            } else {
                println("$theirName: ${entities.goodbye}")
                println("")
                break
            }
        }

        println("\nChoose your response:")
        current.next.forEachIndexed { index, option ->
            println("${index +1}, ${option.prompt}")
        }
        println("")

        val input = readLine()?.toIntOrNull()?.minus(1)

        if (input != null && input in current.next.indices) {
            stack.addLast(current)
            println("You: ${current.next[input].prompt}")
            Thread.sleep(250)
            current = current.next[input]
            println("")
            println("")
        } else {
            println("$theirName: I don't understand...")
        }
    }
}