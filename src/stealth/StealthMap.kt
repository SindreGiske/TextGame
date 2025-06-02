package stealth

import stealth.models.data.BasicTile
import stealth.models.data.Tile

class StealthMap(
    val width: Int,
    val height: Int,
    val startingPoint: Tile,
    val obstacles: List<BasicTile> = listOf(),
    val exits: List<BasicTile> = listOf(),
) {

 }
