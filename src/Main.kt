import model.interactions.runDialogue
import model.util.clearConsole
import java.util.*

var level: Int = 1
fun nextLevel() {
    clearConsole()
    println("""
        Level $level complete!
        
    """.trimIndent())
    level++
    main()
}

fun main() {
    var (_, player) = setupWorld(level)

    if (level == 1) {
        println(
            """
                
        Welcome to the Game. type 'help' for commands.
        
        """.trimIndent())
    }
    if (level == 2) {
        println("""
            
            to initiate dialogue, type 'speak'
            
        """.trimIndent())
    }

    println(player.currentRoom.describe())

    val scanner = Scanner(System.`in`)
    var running = true

    while (running) {
        println(" ")
        print("> ")
        val input = scanner.nextLine().trim().lowercase()
        if (input.isEmpty()) continue

        if (input == "quit") {
            println("Shutting down...")
            running = false
        }

        when {
            input == "help" -> {
                println("""
                    Commands : 
                    - go [direction]
                    - unlock [door direction]
                    - inspect [asset]
                    - inspect door [direction]
                    - take [item] from [asset]
                    - use [item] on [asset]
                    - interact [asset]
                    - inventory
                    - look              (looks around the room)
                    - look at [asset]   (same as inspect [asset])
                    - speak
                    - craft
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

            input.startsWith("look") -> {
                if (input.removePrefix("look").trim() == "")
                println(player.currentRoom.describe())
                else {
                    val asset = input.removePrefix("look at").trim()
                    println(asset)
                    if (asset == "") {
                        println("What do you wish to look at?")
                    } else {
                    println(player.inspectAsset(asset))
                    }
                }
            }
            input.startsWith("inspect ") -> {
                val assetName = input.removePrefix("inspect ").trim()
                if (assetName.startsWith("door ")) {
                    val direction = assetName.removePrefix("door ").trim()
                    println(player.inspectDoor(direction))
                } else
                println(player.inspectAsset(assetName))
            }
            input.startsWith("take ") -> {
                val parts = input.removePrefix("take ").split(" from ", limit = 2)
                if (parts.size == 2) {
                    val item = parts[0].trim()
                    val asset = parts[1].trim()
                    println(player.takeItemFromAsset(item, asset))
                } else
                if (parts.size == 1) {
                    val item = parts[0].trim()
                    println(player.takeItemFromAssetCache(item))
                }
                else {
                    println("Couldn't find [item] on [asset].")
                }
            }
            input.startsWith("use ") -> {
                val parts = input.removePrefix("use ").split(" on ", limit = 2)
                if (parts.size == 2) {
                    val item = parts[0].trim()
                    val asset = parts[1].trim()
                    println(player.useItemOnAsset(item, asset))
                } else {
                    println("You couldn't use [item] on [asset].")
                }
            }

            input.startsWith("interact ") -> {
                val assetName = input.removePrefix("interact ").trim()
                println(player.interact(assetName))
            }

            input == "craft" -> {
                player.craft()
            }

            input == "speak" -> {
                player.initiateDialogue()
            }

            input == "inventory" -> {
                println(player.checkInventory())
            }

            input == "check inventory" -> {
                println(player.checkInventory())
            }

            input == "shortcut" -> {
                nextLevel()
            }
        }
    }
}