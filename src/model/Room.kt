package model

import model.data.Entities

class Room (
    val name: String,
    var description: String,
    var newDescription: String? = "",
    var entities: Entities? = null
){
    val assets: MutableList<Asset> = mutableListOf()
    val exits: MutableMap<String, Door> = mutableMapOf()

    fun connectRoom(direction: String, door: Door) {
        exits[direction] = door
    }

    fun characterLeaves() {
        entities = null
    }

    fun updateDescription() {
        if (newDescription != "") {
            description = newDescription!!
            println(describe())
        }
    }

    fun describe(): String {
        val assetNames = assets.joinToString(", ") { it.name }
        val visibleExits = exits.filterValues { !it.isHidden() }
        val exitNames = visibleExits.keys.joinToString(", ")

        return """
        You are in: $name
        $description

        Assets: ${if (assetNames.isEmpty()) "None" else assetNames}
        Exits: ${if (exitNames.isEmpty()) "None" else exitNames}
        
    """.trimIndent()
    }

    fun findAsset(assetName: String): Asset? {
        return assets.find { it.name.equals(assetName, ignoreCase = true) }
    }

    fun findDoor(doorName: String): Door? {
        return exits[doorName]
    }
}