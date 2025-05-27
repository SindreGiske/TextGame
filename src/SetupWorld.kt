import levels.level1
import model.*

fun setupWorld(level: Int): Pair<Room, Player> {

    var startingRoom: Room = Room("", "")

    when (level) {
        1 -> {
            level1()
            startingRoom = level1()
        }
    }
    val player = Player(startingRoom)
    return Pair(startingRoom, player)
}

