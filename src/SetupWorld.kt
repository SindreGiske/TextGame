import model.*
import model.data.Interaction
import model.data.Item
import model.data.LockTypeEnum


fun setupWorld(): Pair<Room, Player> {

    val startingRoom = Room("Cell", "A dark cell.", exitText = "You unlock the door with the key and leave the room.")
    val table = Asset("table", "It's a table. There seems to be some clutter on it.")
    val cellKey = Item("key", "Looks rusty, but should still be able to open some locks.")
    table.addItem(cellKey)
    startingRoom.assets.add(table)

    val picture = Asset("Picture", "It's a small painting of a wooden wall. Least interesting thing you've seen in a while.")
    val look = Interaction("You look at the painting. It's pretty dull. Nothing happends.")
    picture.setInteractions(look)
    startingRoom.assets.add(picture)

    val hallway = Room("Hallway", "There are three doors here. One you came out of, one on each side of you.",
        LockTypeEnum.item, lockKey = cellKey)

    val westRoom = Room("storage", "It's just an almost empty storage room with a lever on the wall.")
    val lever = Asset("lever", "Just a lever. ")
    val pull = Interaction("you pull the lever, and hear a big clunk behind you.")
    lever.setInteractions(pull)

    westRoom.assets.add(lever)

    val eastRoom = Room("east room", "To be continued.", LockTypeEnum.interaction, lockInteraction = lever)

    startingRoom.connectRoom("north", hallway)
    hallway.connectRoom("south", startingRoom)
    hallway.connectRoom("west", westRoom)
    hallway.connectRoom("east", eastRoom)
    westRoom.connectRoom("east", hallway)
    eastRoom.connectRoom("west", hallway)


    val player = Player(startingRoom)
    return Pair(startingRoom, player)

}

