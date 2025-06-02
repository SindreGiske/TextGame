package stealth.models.data

interface Tile {
    val height: Int
    val width: Int
    val obstructed: Boolean
}

data class BasicTile(
    override val height: Int,
    override val width: Int,
    override val obstructed: Boolean = false
) : Tile