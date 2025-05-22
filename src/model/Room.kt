package model

class Room (
    val name: String,
    val description: String,
){
    val assets: MutableList<Asset> = mutableListOf()
    val exits: MutableMap<String, Door> = mutableMapOf()

    fun connectRoom(direction: String, door: Door) {
        exits[direction] = door
    }

    fun describe(): String {
        val assetNames = assets.joinToString(", ") { it.name }

        // Get visible exits only
        val visibleExits = exits.filterValues { !it.isHidden() }
        val exitNames = visibleExits.keys.joinToString(", ")

        return """
        $name
        $description

        Assets: ${if (assetNames.isEmpty()) "None" else assetNames}
        Exits: ${if (exitNames.isEmpty()) "None" else exitNames}
    """.trimIndent()
    }

    fun findAsset(assetName: String): Asset? {
        return assets.find { it.name.equals(assetName, ignoreCase = true) }
    }
}