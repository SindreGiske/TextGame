package model.entities

import model.data.DialogueNode
import model.data.Entities

val entityTemplate = Entities(
    name = "name",
    goodbye = "goodbye",
    event = null,
    rootDialogue = DialogueNode(
        prompt = "",
        response = "",
        next = listOf(
            DialogueNode(
                prompt = "",
                response = ""
            ),
            DialogueNode(
                prompt = "",
                response = "",
                next = listOf(
                    DialogueNode(
                        prompt = "",
                        response = "",
                        goBackOnEnd = true
                    ),
                    DialogueNode(
                        prompt = "",
                        response = "",
                        triggerEvent = true
                    )
                )
            )
        )
    )
)