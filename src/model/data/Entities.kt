package model.data

import model.interactions.Event

data class Entities (
    val name: String,
    val goodbye: String,
    val rootDialogue: DialogueNode,
    var event: Event? = null
)