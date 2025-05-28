package levels

import model.Door
import model.Room
import model.data.Item

fun levelTemplate(): Room {
    val doorList = mutableListOf<Door>()
    val itemList = mutableListOf<Item>()

    //STARTROOM INIT
    val start = Room("name", "description")

    //SECONDROOM INIT
    val secondRoom = Room("secondRoom", "description")

    //DOORS

    //ENTITIES

    //EVENTS


    val startingRoom: Room = start
    return startingRoom
}