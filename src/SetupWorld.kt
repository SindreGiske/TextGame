import model.*
import model.data.Item
import model.data.LockTypeEnum

fun setupWorld(): Pair<Room, Player> {
    val doorList = mutableListOf<Door>()

    //CELL INIT AND ASSETS
    val cell = Room("Cell", """
                You are standing in a small cell.
        There is a painting on one wall, a table, and a door on the north wall.
        
                            N
                        ╔══───══╗
                        ║│    ╥ ║
                        ║│      ║
                        ╚═══════╝
        
    """.trimIndent())
    val table = Asset("table", "It's a table. There seems to be some clutter on it.")
    val cellKey = Item("key", "Looks rusty, but should still be able to open some locks.")
    table.addItem(cellKey)
    cell.assets.add(table)
    val painting = Asset("painting", ("""
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
    cell.assets.add(painting)

    //HALLWAY INIT
    val hallway = Room("Hallway",
        """There are three doors here. One you came out of, one to the west and one north.
           The North door has two lit candle light lamps on each side of the north door. 
           
                    N
                ╔══───══╗
                ║±     ±║
                ║       ║
              W │       ║
                ║       ║
                ╚══───══╝
                    S
                    
        """.trimMargin(),)

    //STORAGE CLOSET INIT AND ASSETS
    val westRoom = Room("storage closet", """
        It's just an almost empty storage room with a lever on the wall.
        
                        ╔═══════╗
                        ║╔╧╧╧╧╧╧║
                        ║╢      │ E
                        ║╚╤╤╤╕ ┴║
                        ╚═══════╝
        """.trimIndent())
    val lever = Asset("lever", "Just a lever. ",
        interaction = "you pull the lever, and hear a big clunk from the hallway.")

    //RITUAL ROOM INIT AND ASSETS
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
                               S
                               
        """.trimIndent(),
        """
      The wall behind the obelisks opened up, and you see a big hall on the other side.
                                
                               N
                        ╔════     ════╗
                        ║ Φ    ⌠    Ω ║
                        ║ ▓    ▓    ▓ ║
                        ║             ║
                        ║      ╥      ║
                        ║             ║
                        ╚═════   ═════╝
                               S
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
           Around hip height it holds a plate with a single thick candle on it. 
           
        """.trimMargin(), interaction ="light the candle", interactionItem = matchbox)
    ritualRoom.assets.add(eagleObelisk)
    val snakeObelisk = Asset("Snake Obelisk",
        """A tall black obelisk with an inscribed drawing of a Snake.
           Around hip height it holds a plate with a single thick on it. 
           
        """.trimMargin(), interaction ="light the candle", interactionItem = matchbox)
    ritualRoom.assets.add(snakeObelisk)
    val toadObelisk = Asset("Toad Obelisk",
        """A tall black obelisk with an inscribed drawing of a Toad.
           Around hip height it holds a plate with a single thick on it. 
           
        """.trimMargin(), interaction ="light the candle", interactionItem = matchbox)
    ritualRoom.assets.add(toadObelisk)

    //ALTER HALL INIT
    val alterHall = Room("Alter Hall", """
            
                        ╔═════════════════╗
                        ║                 ║
                        ║       ▐█▌       ║
                        ║   ■         ■   ║
                        ║                 ║
                        ║   ■         ■   ║
                        ║                 ║
                        ║   ■         ■   ║
                        ║                 ║
                        ║   ■         ■   ║
                        ║                 ║
                        ╚══╗           ╔══╝
                           ║     ╥     ║
                           ║           ║
                           ╚════   ════╝
                           
    """.trimIndent())


    // EVENTS

    val pullLever = Event("Pull")
    pullLever.addAsset(lever)
    westRoom.assets.add(lever)

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
    val northDoor = Door.makeDoor("Heavy Door north of the Hallway.", roomA= hallway, roomB = ritualRoom,
        lockType = LockTypeEnum.interaction, direction = "north",
                description = """
            
                An Ominous Dark metal slab 
        with an eye that follows you wherever you go.
        It does not seem to have any locks or doorhandles.
        
                ╒═╤═╤═╤═╤╤═╤═╤═╤═╕
                │▓▓▒▒▒░░░░░░▒▒▒▓▓│
                │▒▒▒░░░░░░░░░░▒▒▒│
                │▒░░░░      ░░░░▒│
                │░░░   ▄██▄   ░░░│
                │░░░   ▀██▀   ░░░│
                │▒░░░░      ░░░░▒│
                │▒▒▒░░░░░░░░░░▒▒▒│
                │▓▓▒▒▒░░░░░░▒▒▒▓▓│
                ╘═╧═╧═╧═╧╧═╧═╧═╧═╛
            
        """.trimIndent())
    doorList.add(northDoor)
    pullLever.addDoor(northDoor)
    val ritualToAltar = Door.makeDoor("Ritual Door", roomA = ritualRoom, roomB = alterHall,
        "north", lockType = LockTypeEnum.none, hidden = true,
        description = """
            
                An Ominous Dark metal slab 
        with an eye that follows you wherever you go.
                ╒═╤═╤═╤═╤╤═╤═╤═╤═╕
                │▓▓▒▒▒░░░░░░▒▒▒▓▓│
                │▒▒▒░░░░░░░░░░▒▒▒│
                │▒░░░░      ░░░░▒│
                │░░░   ▄██▄   ░░░│
                │░░░   ▀██▀   ░░░│
                │▒░░░░      ░░░░▒│
                │▒▒▒░░░░░░░░░░▒▒▒│
                │▓▓▒▒▒░░░░░░▒▒▒▓▓│
                ╘═╧═╧═╧═╧╧═╧═╧═╧═╛
            
        """.trimIndent())
    doorList.add(ritualToAltar)
    allLit.addDoor(ritualToAltar)

    // GAME INIT
    val startingRoom: Room = cell
    val player = Player(startingRoom)
    return Pair(startingRoom, player)
}