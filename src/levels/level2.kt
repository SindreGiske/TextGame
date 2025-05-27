package levels

import model.Door
import model.Room
import model.entities.oldMan
import model.interactions.Event

fun level2(): Room {

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
                               
                               N
                        ╔═════   ═════╗
                        ║ Φ    ⌠    Ω ║
                        ║ ▓    ▓    ▓ ║
                        ║             ║
                        ║      ╥      ║
                        ║             ║
                        ╚═════   ═════╝
                               S
 
        """.trimIndent()), entities = oldMan )

    val introEvent = Event("introEvent", entityEventRoom = start)
    oldMan.event = introEvent

    val backRoom = Room("Backroom", "")


    val ritualBackDoor = Door("Ritual Hall Backdoor", roomA = start, roomB = backRoom, hidden = true)
    introEvent.addDoor(ritualBackDoor)

    val startingRoom: Room = start
    return startingRoom
}