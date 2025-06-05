package model.data

import model.interactions.Event

data class NPCs (
    val name: String,
    val goodbye: String,
    val rootDialogue: DialogueNode,
    var event: Event? = null,
    val leavesAfterDialogue: Boolean
) {
    fun updateEvent(event: Event) {
        this.event = event
    }
}