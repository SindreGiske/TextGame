package stealth.models.data.rooms

import stealth.StealthMap
import stealth.models.data.BasicTile

val room1 = StealthMap(
    height = 9,
    width = 7,
    startingPoint = BasicTile(0, 0),
    obstacles = listOf(
        BasicTile(1, 0, true),
        BasicTile(1, 1, true),
        BasicTile(1, 2, true),
        BasicTile(2, 2, true),
        BasicTile(3, 2, true),
        BasicTile(3, 3, true),
        BasicTile(3, 4, true),
        BasicTile(4, 4, false),
    ),
    exits = listOf(
        BasicTile(0, 0),
        BasicTile(6, 8)
    )

)