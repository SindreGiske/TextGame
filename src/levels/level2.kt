package levels

import model.Room
import model.characters.oldMan

fun level2(): Room {

    val start = Room("Ritual Hall", """
        
        A great gust of wind blew through the hall, blowing out all the candles.
        The room is completely dark. You blink slowly, but whether your eyes are
                        open or not makes no difference. 
        
                    Suddenly you hear a voice in the darkness. 
                    
    """.trimIndent(), character = oldMan
    )



    val startingRoom: Room = start
    return startingRoom
}