package model.npc

import model.data.DialogueNode
import model.data.NPCs

val Jeff = NPCs(
    name = "Jeff",
    goodbye = "Gotta run. To be honest I'm just here for the pay. Take this key, might help give you a chance to survive.",
    event = null,
    rootDialogue = DialogueNode(
        response = "Hey man, please don't snitch on me for smoking.",
        next = listOf(
            DialogueNode(
                prompt = "Who are you and why are you sneaking around in here?",
                response = "Name's Jeff and I'm sneaking a cigarette. Who are YOU?",
                goBackOnEnd = true
            ),
            DialogueNode(
                prompt = "I... Don't know who I am. What is this place?",
                response = "One of those, huh? Too bad. Thought you were just a new hire.",
                next = listOf(
                    DialogueNode(
                        prompt = "What do you mean one of those?",
                        response = "Experiments. The Elders are experimenting with black magic. " +
                                "Guess you were just at the wrong place at the wrong time.",
                        triggerEvent = true
                    )
                )
            ),
            DialogueNode(
                prompt = "Mind if I bum one?",
                response = "Nu-uh bro do you even know how hard it is to smuggle these in here?",
                goBackOnEnd = true
            )
        )
    )
)