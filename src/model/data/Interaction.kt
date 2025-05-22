package model.data

data class Interaction(
    val activation: String,
    val itemActivated: Boolean = false,
    val neededItem: Item? = null,
)