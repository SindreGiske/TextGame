import model.*
import java.util.*

fun main() {
    val (_, player) = setupWorld()
    println("Welcome to the Game. type 'help' for commands.")
    println(player.currentRoom.describe())

    val scanner = Scanner(System.`in`)

    while (true) {
        print("> ")
        val input = scanner.nextLine().trim().lowercase()
        if (input.isEmpty()) continue

        when {
            input == "help" -> {
                println("""
                    Commands : 
                    - go [direction]
                    - unlock [door direction]
                    - inspect [asset]
                    - take [item] from [asset]
                    - interact [asset]
                    - inventory
                    - look
                    - quit
                """.trimIndent())
            }
            input.startsWith("go ") -> {
                val direction = input.removePrefix("go ").trim()
                println(player.move(direction))
            }
            input.startsWith("unlock ") -> {
                val direction = input.removePrefix("unlock ").trim()
                println(player.unlockDoor(direction))
            }

            input == "look" -> {
                println(player.currentRoom.describe())
            }
            input.startsWith("inspect ") -> {
                val assetName = input.removePrefix("inspect ").trim()
                println(player.inspectAsset(assetName))
            }
            input.startsWith("take ") -> {
                val parts = input.removePrefix("take ").split(" from ", limit = 2)
                if (parts.size == 2) {
                    val item = parts[0].trim()
                    val asset = parts[1].trim()
                    println(player.takeItemFromAsset(item, asset))
                } else {
                    println("Couldn't find [item] on [asset].")
                }
            }
            input.startsWith("interact ") -> {
                val assetName = input.removePrefix("interact ").trim()
                println(player.interact(assetName))
            }

            input == "inventory" -> {
                println(player.checkInventory())
            }
            input == "quit" -> {
                println("Shutting down...")
                break
            }

            else -> println("""
                Invalid input. 
                One of us is too stupid to understand what's going on...
                Type 'help' for commands.""".trimIndent())
        }
    }

}