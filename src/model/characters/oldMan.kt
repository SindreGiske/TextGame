package model.characters
import model.data.DialogueNode
import model.data.character

val oldMan = character(
    name = "Mysterious Old Man",
    goodbye = "Godspeed fresh meat!",
    rootDialogue = DialogueNode(
        prompt = "",
        response = "Hello there...",
        next = listOf(
            DialogueNode(
                prompt = "Who are you?!",
                response = "Is that really all you need to know?",
                goBackOnEnd = true
            ),
            DialogueNode(
                prompt = "What am I doing here?!",
                response = "Oh what would be the fun in telling you now?",
                goBackOnEnd = true
            ),
            DialogueNode(
                prompt = "How do I get out of here?!",
                response = ""
            ),
            DialogueNode(
                prompt = "General Kenobi.",
                response = "What did you just call me?",
                goBackOnEnd = true
            ),
        )
    )
)