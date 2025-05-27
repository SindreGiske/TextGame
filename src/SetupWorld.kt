import levels.level1
import levels.level2
import model.*

fun setupWorld(level: Int): Pair<Room, Player> {

    var startingRoom: Room = Room("", "")

    when (level) {
        1 -> {
            level1()
            startingRoom = level1()
        }
        2 -> {
            level2()
            startingRoom = level2()
        }
    }
    val player = Player(startingRoom)
    return Pair(startingRoom, player)
}

