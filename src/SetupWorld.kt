import model.*
import model.data.Item
import model.data.LockTypeEnum

public fun setupWorld(): Pair<Room, Player> {
    val doorList = mutableListOf<Door>()

    val cell = Room("Cell", "A dark cell.")
    val table = Asset("table", "It's a table. There seems to be some clutter on it.")
    val cellKey = Item("key", "Looks rusty, but should still be able to open some locks.")
    table.addItem(cellKey)
    cell.assets.add(table)
    val picture = Asset("Picture", ("""
        ⢀⡴⠑⡄⠀⠀⠀⠀⠀⠀⠀⣀⣀⣤⣤⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ 
        ⠸⡇⠀⠿⡀⠀⠀⠀⣀⡴⢿⣿⣿⣿⣿⣿⣿⣿⣷⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀ 
        ⠀⠀⠀⠀⠑⢄⣠⠾⠁⣀⣄⡈⠙⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀ 
        ⠀⠀⠀⠀⢀⡀⠁⠀⠀⠈⠙⠛⠂⠈⣿⣿⣿⣿⣿⠿⡿⢿⣆⠀⠀⠀⠀⠀⠀⠀ 
        ⠀⠀⠀⢀⡾⣁⣀⠀⠴⠂⠙⣗⡀⠀⢻⣿⣿⠭⢤⣴⣦⣤⣹⠀⠀⠀⢀⢴⣶⣆ 
        ⠀⠀⢀⣾⣿⣿⣿⣷⣮⣽⣾⣿⣥⣴⣿⣿⡿⢂⠔⢚⡿⢿⣿⣦⣴⣾⠁⠸⣼⡿ 
        ⠀⢀⡞⠁⠙⠻⠿⠟⠉⠀⠛⢹⣿⣿⣿⣿⣿⣌⢤⣼⣿⣾⣿⡟⠉⠀⠀⠀⠀⠀ 
        ⠀⣾⣷⣶⠇⠀⠀⣤⣄⣀⡀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀ 
        ⠀⠉⠈⠉⠀⠀⢦⡈⢻⣿⣿⣿⣶⣶⣶⣶⣤⣽⡹⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀ 
        ⠀⠀⠀⠀⠀⠀⠀⠉⠲⣽⡻⢿⣿⣿⣿⣿⣿⣿⣷⣜⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀ 
        ⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣷⣶⣮⣭⣽⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀ 
        ⠀⠀⠀⠀⠀⠀⣀⣀⣈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀ 
        ⠀⠀⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀ 
        ⠀⠀⠀⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀ 
        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠻⠿⠿⠿⠿⠛⠉
        BIG BROTHER IS ALWAYS WATCHING
    """.trimIndent()))
    cell.assets.add(picture)

    val hallway = Room("Hallway", "There are three doors here. One you came out of, one to the west and one north.",)

    val westRoom = Room("storage", "It's just an almost empty storage room with a lever on the wall.")
    val lever = Asset("lever", "Just a lever. ",
        interaction = "you pull the lever, and hear a big clunk from the hallway.")
    val pullLever = Event("Pull")
    pullLever.addAsset(lever)
    westRoom.assets.add(lever)

    val ritualRoom = Room("Ritual Hall",
        """
                A dim candlelit hall. 
              There is a table in front of you and you see 
        three ominous looking obelisks on the other side of the room.
            
                        ╔═════════════╗
                        ║ Φ    ⌠    Ω ║
                        ║ ▓    ▓    ▓ ║
                        ║             ║
                        ║      ╥      ║
                        ║             ║
                        ╚═════   ═════╝
        """.trimIndent(),
        """
      The wall behind the obelisks opened up, and you see a big hall on the other side.

                        ╔════     ════╗
                        ║ Φ    ⌠    Ω ║
                        ║ ▓    ▓    ▓ ║
                        ║             ║
                        ║      ╥      ║
                        ║             ║
                        ╚═════   ═════╝
        """.trimIndent())

    val ritualTable = Asset("table", """
        A small table with a half circle of candles.
        Inside there is a silver plate with a single matchbox on it.
        """.trimIndent())
    val matchbox = Item("matchbox", "A small matchbox with a handful of matches in it.")
    ritualTable.addItem(matchbox)
    ritualRoom.assets.add(ritualTable)

    val eagleObelisk = Asset("Eagle Obelisk",
        """A tall black obelisk with an inscribed drawing of an Eagle.
           Around hip height it holds a plate with an candle on it. 
           
        """.trimMargin(), interaction ="light candle", interactionItem = matchbox)
    ritualRoom.assets.add(eagleObelisk)

    val snakeObelisk = Asset("Snake Obelisk",
        """A tall black obelisk with an inscribed drawing of a Snake.
           Around hip height it holds a plate with an candle on it. 
           
        """.trimMargin(), interaction ="light candle", interactionItem = matchbox)
    ritualRoom.assets.add(snakeObelisk)

    val toadObelisk = Asset("Toad Obelisk",
        """A tall black obelisk with an inscribed drawing of a Toad.
           Around hip height it holds a plate with an candle on it. 
           
        """.trimMargin(), interaction ="light candle", interactionItem = matchbox)
    ritualRoom.assets.add(toadObelisk)

    val altarHall = Room("Alter Hall", "description")

    val allLit = Event("All lit")
    allLit.addAsset(eagleObelisk)
    allLit.addAsset(snakeObelisk)
    allLit.addAsset(toadObelisk)



    // DOORS

    val cellDoor = Door.makeDoor("Cell Door",roomA= cell, roomB = hallway,
        lockType = LockTypeEnum.item, lockKey = cellKey, direction = "north")
    doorList.add(cellDoor)

    val westDoor = Door.makeDoor("hallWest", roomA= hallway, roomB = westRoom,
        lockType = LockTypeEnum.none, direction = "west")
    doorList.add(westDoor)
    val northDoor = Door.makeDoor("hallNorth", roomA= hallway, roomB = ritualRoom,
        lockType = LockTypeEnum.interaction, direction = "north")
    doorList.add(northDoor)
    pullLever.addDoor(northDoor)
    val ritualToAltar = Door.makeDoor("Ritual Door", roomA = ritualRoom, roomB = altarHall,
        "north", lockType = LockTypeEnum.none, hidden = true)
    doorList.add(ritualToAltar)
    allLit.addDoor(ritualToAltar)


    val startingRoom: Room = ritualRoom
    val player = Player(startingRoom)
    return Pair(startingRoom, player)
}