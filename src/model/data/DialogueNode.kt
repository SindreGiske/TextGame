package model.data

data class DialogueNode(
    val prompt: String,
    val response: String,
    val next: List<DialogueNode> = emptyList(),
    val goBackOnEnd: Boolean = false,
    val triggerEvent: Boolean = false
)
