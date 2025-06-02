package levels

import model.interactions.Event
import model.data.LockTypeEnum
import model.data.Item
import model.npc.oldMan
import model.npc.Jeff
import model.Asset
import model.Door
import model.Room

fun level2(): Room {
    val doorList = mutableListOf<Door>()
    val itemList = mutableListOf<Item>()

    // START ROOM INIT
    val start = Room("Ritual Hall", """
        
        
                        Just as you lit the last candle,
        a great gust of wind blew through the hall, blowing out all the candles.
        The room is completely dark. You blink slowly, but whether your eyes are
                   open or not makes no difference whatsoever. 
        
                    Suddenly you hear a voice in the darkness. 
                 
                    
    """.trimIndent(),
        newDescription = ("""
        
                As quickly as the candles blew out,
         they all burst back into flame, lighting up the room,
        and revealing an opening in the wall behind the obelisk.
        
                        You are all alone.
                               
                               N
                        ╔═════   ═════╗
                        ║ Φ    ⌠    Ω ║
                        ║ ▓    ▓    ▓ ║
                        ║             ║
                        ║      ╥      ║
                        ║             ║
                        ╚═════───═════╝
                               
 
        """.trimIndent()))

    // BACKROOM INIT
    val backRoom = Room("Backroom", """
        
                    Behind the Ritual Hall you find what looks like some sort of 
            waiting lounge. On the north wall there are four tables in front of a bookshelf. 
                       In front of the west door there are some wooden crates.         
                
                                        ╔╤╤╤╤╤╤╤╤╤╤╤╤╤╗
                               ╔════════╝╧╧╧╧╧╧╧╧╧╧╧╧╧╚════════╗
                               ║ ▓▓▓▓▓▓  ╥   ╥   ╥   ╥         ║
                             W │                               │ E
                               ║▓▓                             ║
                               ╚════════╗             ╔════════╝
                                        ╚═════   ═════╝
                                               S
                
    """.trimIndent())
    val backRoomCrates = Asset("Crates",
        "Heavy wooden crates. The lids are nailed down. On one of the crates you see a key.")
    val backToStorageKey = Item("Storage room key", "Pretty normal key on a keychain with a 'storage' label.")
    backRoomCrates.addItem(backToStorageKey)
    backRoom.assets.add(backRoomCrates)
    itemList.add(backToStorageKey)

    // STORAGE ROOM INIT
    val storageRoom = Room("Storage Room", """
        
        A large storage room with filled with shelves from wall to wall. 
                    The room is cluttered with wooden crates. 
                    
         Looking around the room you freeze in fear as you turn a corner
         and see a man sitting on one of the crates smoking a cigarette. 
        
                           ╔════════════════════╗
                           ║╔╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧║   
                           ║╢   ╔╤╤▓▓        ▓▓ ║
                           ║╢   ╟╔╧╧╝     ▓▓▓▓▓▓║
                           ║╢   ╟╢      ╔═══════╝
                           ║╢   ▓▓▓     ║
                           ║╢           │ E
                           ║╚╤╤╤╤╤╤╤╕ ▓▓║
                           ╚════════════╝
        
        
    """.trimIndent(),
        newDescription = """
        
         A large storage room with filled with shelves from wall to wall. 
                 The room is cluttered with wooden crates. 
                    
         The nice man left a key on the crate he sat on before he left. 
        
                           ╔════════════════════╗
                           ║╔╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧╧║   
                           ║╢   ╔╤╤▓▓        ▓▓ ║
                           ║╢   ╟╔╧╧╝     ▓▓▓▓▓▓║
                           ║╢   ╟╢      ╔═══════╝
                           ║╢   ▓▓▓     ║
                           ║╢           │ E
                           ║╚╤╤╤╤╤╤╤╕ ▓▓║
                           ╚════════════╝
        
       
    """.trimIndent())
    val westRoomCrates = Asset("Crates", "Heavy wooden crates. The lids are nailed down. ")
    storageRoom.assets.add(westRoomCrates)
    val westRoomWallShelves = Asset("Wall shelves",
        "A series of shelves spanning almost all of the south, west and north walls.")
    storageRoom.assets.add(westRoomWallShelves)
    val jeffsKey = Item("Jeff's key", "He said this key could give you a chance.")
    val jeffsCrate = Asset("Jeff's crate", "The crate Jeff sat on. He left a key for you on it. ")
    jeffsCrate.addItem(jeffsKey)

    //DOORS
    val ritualBackDoor = Door.makeDoor("Ritual Hall Backdoor", roomA = start, roomB = backRoom,
        hidden = true, direction = "north", lockType = LockTypeEnum.none)
    doorList.add(ritualBackDoor)

    val backRoomStorageDoor = Door.makeDoor("Storage room door", roomA = backRoom, roomB = storageRoom,
        direction = "west", lockType = LockTypeEnum.item, lockKey = backToStorageKey)
    doorList.add(backRoomStorageDoor)



    //ENTITIES
    val oldMan = oldMan
    val jeff = Jeff

    //EVENTS
    val introEvent = Event("introEvent", entityEventRoom = start, activationText = "")
    introEvent.addDoor(ritualBackDoor)
    oldMan.updateEvent(introEvent)
    start.setNPC(oldMan)

    val jeffEvent = Event("jeffEvent", entityEventRoom = storageRoom, activationText = "")
    jeffEvent.addAsset(jeffsCrate)
    jeff.updateEvent(jeffEvent)
    storageRoom.setNPC(jeff)


    val startingRoom: Room = start
    return startingRoom
}