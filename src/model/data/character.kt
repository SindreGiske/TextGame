package model.data

import model.interactions.Event

data class character (
    val name: String,
    val goodbye: String,
    val rootDialogue: DialogueNode,
    val event: Event? = null
)