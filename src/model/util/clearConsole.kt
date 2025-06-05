package model.util

fun clearConsole() {
    if (System.console() == null) {
        // You're in IntelliJ or unsupported terminal
        repeat(50) { println() }
    } else {
        print("\u001b[H\u001b[2J")
        System.out.flush()
    }
}