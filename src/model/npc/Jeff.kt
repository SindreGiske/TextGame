package model.npc

import model.data.DialogueNode
import model.data.NPCs

val Jeff = NPCs(
    name = "Jeff",
    goodbye = "see ya'!",
    event = null,
    rootDialogue = DialogueNode(
        response = "Hey man, please don't snitch on me for smoking.",
        next = listOf(
            DialogueNode(
                prompt = "Who are you and why are you sneaking around in here?",
                response = "Name's Jeff and I'm sneaking a cigarette. Who are YOU?"
            ),
            DialogueNode(
                prompt = "I... Don't know who I am. What is this place?",
                response = "One of those, huh? Too bad, ",
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
            ),
            DialogueNode(
                prompt = "Mind if I bum one?",
                response = "Nu-uh bro do you even know how hard it is to smuggle these in here?",
            )
        )
    )
)