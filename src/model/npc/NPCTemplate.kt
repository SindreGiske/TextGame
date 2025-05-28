package model.npc

import model.data.DialogueNode
import model.data.NPCs

val entityTemplate = NPCs(
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