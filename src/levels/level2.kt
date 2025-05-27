package levels

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
                    
    """.trimIndent(), entities = oldMan
    )
    val ritualRoom = Room("Ritual Hall",
        """
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
 
        """.trimIndent())

    val introEvent = Event("introEvent", movePlayer = ritualRoom)
    oldMan.event = introEvent

    val startingRoom: Room = start
    return startingRoom
}