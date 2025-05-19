import model.*
import model.data.Interaction
import model.data.Item
import model.data.LockTypeEnum


fun setupWorld(): Pair<Room, Player> {
    var doorList = mutableListOf<Door>()

    val startingRoom = Room("Cell", "A dark cell.")
    val table = Asset("table", "It's a table. There seems to be some clutter on it.")
    val cellKey = Item("key", "Looks rusty, but should still be able to open some locks.")
    table.addItem(cellKey)
    startingRoom.assets.add(table)
    val picture = Asset("Picture", "It's a small painting of a wooden wall. Least interesting thing you've seen in a while.")
    val look = Interaction("You look at the painting. It's pretty dull. Nothing happends.")
    picture.setInteractions(look)
    startingRoom.assets.add(picture)

    val hallway = Room("Hallway", "There are three doors here. One you came out of, one on each side of you.",)

    val westRoom = Room("storage", "It's just an almost empty storage room with a lever on the wall.")
    val lever = Asset("lever", "Just a lever. ")
    val pull = Interaction("you pull the lever, and hear a big clunk behind you.")
    lever.setInteractions(pull)

    westRoom.assets.add(lever)

    val eastRoom = Room("east room", "To be continued.")

    val cellDoor = Door.makeDoor("cellHall",roomA= startingRoom, roomB = hallway,
        lockType = LockTypeEnum.item, lockKey = cellKey, direction = "north")
    doorList.add(cellDoor)
    val westDoor = Door.makeDoor("hallWest", roomA= hallway, roomB = westRoom,
        lockType = LockTypeEnum.none, direction = "west")
    doorList.add(westDoor)
    val eastDoor = Door.makeDoor("hallEast", roomA= hallway, roomB = eastRoom,
        lockType = LockTypeEnum.interaction, lockInteraction = lever, direction = "east")
    doorList.add(eastDoor)

    val player = Player(startingRoom)
    return Pair(startingRoom, player)

}

