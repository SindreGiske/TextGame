package model.data

import model.interactions.Event

data class NPCs (
    val name: String,
    val goodbye: String,
    val rootDialogue: DialogueNode,
    var event: Event? = null
) {
    fun updateEvent(event: Event) {
        this.event = event
    }
}