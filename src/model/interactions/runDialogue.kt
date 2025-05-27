package model.interactions

import model.data.DialogueNode
import model.data.character

fun runDialogue( character: character ) {
    val stack = ArrayDeque<DialogueNode>()
    var current: DialogueNode? = character.rootDialogue
    val theirName = character.name

    while (current != null) {
        println("$theirName: ${current.response}")

        if (current.triggerEvent == true) {
            character.event?.characterActivateEvent()
        }

        if (current.next.isEmpty()) {
            if (current.goBackOnEnd && stack.isNotEmpty()) {
                current = stack.removeLast()
                continue
            } else {
                println("$theirName: ${character.goodbye}")
                break
            }
        }

        println("\nChoose your response:")
        current.next.forEachIndexed { index, option ->
            println("${index +1}, ${option.prompt}")
        }

        val input = readLine()?.toIntOrNull()?.minus(1)

        if (input != null && input in current.next.indices) {
            stack.addLast(current)
            current = current.next[input]
        } else {
            println("$theirName: I don't understand...")
        }
    }
}