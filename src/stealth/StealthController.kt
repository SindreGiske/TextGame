package stealth

import stealth.models.data.BasicTile

class StealthController {

    fun initiateMap(map: StealthMap) {
        val tiles = mutableSetOf<BasicTile>()
        val h = map.height
        val w = map.width

        for (y in 0 until h) {
            for (x in 0 until w) {
                val isObstructed = map.obstacles.contains(BasicTile(y, x, true))
                val tile = BasicTile(y, x, isObstructed)
                tiles.add(tile)
            }
        }

        for (y in 0 until h) {
            val line = mutableListOf<String>()
            for (x in 0 until w) {
                val tile = tiles.find { it.height == y && it.width == x }
                if (tile?.obstructed == true) {
                    line.add("▓${x}▓")
                } else if(
                    map.exits.contains(BasicTile(x, y))
                ) { line.add("[∩]") }
                else {
                    line.add(" ${x} ")
                }
            }
            line.addFirst("//")
            line.addLast("//")
            println(line.joinToString(""))
        }
    }
}