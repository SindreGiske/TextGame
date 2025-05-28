package levels

import model.Door
import model.Room
import model.data.LockTypeEnum
import model.npc.oldMan
import model.interactions.Event

fun level2(): Room {
    val doorList = mutableListOf<Door>()

    // STARTROOM INIT
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
        
        
                
                ╔╤╤╤╤╤╤╤╤╤╤╤╤╤╗
       ╔════════╝╧╧╧╧╧╧╧╧╧╧╧╧╧╚════════╗
       ║         ╥   ╥   ╥   ╥         ║
     W │                               │ E
       ║                               ║
       ╚════════╗             ╔════════╝
                ╚═════   ═════╝
                       S
                
               
        
    """.trimIndent())

    //DOORS
    val ritualBackDoor = Door.makeDoor("Ritual Hall Backdoor", roomA = start, roomB = backRoom, hidden = true, direction = "north", lockType = LockTypeEnum.none)
    doorList.add(ritualBackDoor)

    //ENTITIES
    val oldMan = oldMan

    //EVENTS
    val introEvent = Event("introEvent", entityEventRoom = start, activationText = "")
    introEvent.addDoor(ritualBackDoor)
    oldMan.updateEvent(introEvent)
    start.setNPC(oldMan)

    val startingRoom: Room = start
    return startingRoom
}