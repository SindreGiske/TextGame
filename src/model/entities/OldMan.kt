package model.entities
import model.data.DialogueNode
import model.data.Entities

val oldMan = Entities(
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
                response = "Now that's a funny question. The short answer; YOU DON'T!!",
                next = listOf(
                    DialogueNode(
                        prompt = "What do you mean?! Why can't I get out if here?!",
                        response = "Well, you can, but I'd bet my left eye you won't.",
                        next = listOf(
                            DialogueNode(
                                prompt= "I don't wanna play your stupid game, just kill me already.",
                                response = "You're no fun at all. I have other business to attend to. Perhaps I'll see you again later.",
                                triggerEvent = true
                            ),
                            DialogueNode(
                                prompt = "Stop playing around. Let me see you so I can strangle you!!!",
                                response = "You're a feisty one, aren't ya'! If you're lucky you'll get the chance soon enough.",
                                triggerEvent = true
                            )
                        )
                    )
                )
            )
        )
    )
)