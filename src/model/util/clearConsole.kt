package model.util

fun clearConsole() {
    repeat (50) { println() }
    print("\u001b[H\u001b[2J")
    System.out.flush()
}