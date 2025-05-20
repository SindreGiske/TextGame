package model.util

fun String.reverseDirection(): String {
    return when (this.lowercase()) {
        "north" -> "south"
        "south" -> "north"
        "east" -> "west"
        "west" -> "east"
        "up" -> "down"
        "down" -> "up"
        else -> "unknown"
    }
}