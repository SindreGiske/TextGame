package model

import model.data.Item
import model.data.LockTypeEnum

class Room (
    val name: String,
    val description: String,
    var lockType: LockTypeEnum = LockTypeEnum.none,
    val lockKey: Item? = null,
    var lockInteraction: Asset? = null,
    val exitText: String = ""
){
    val assets: MutableList<Asset> = mutableListOf()
    val exits: MutableMap<String, Room> = mutableMapOf()

    fun connectRoom(direction: String, room: Room) {
        exits[direction] = room
    }

    fun updateLockType(lockType: LockTypeEnum, lockInteraction: Asset?) {
        this.lockType = lockType
        if (lockType == LockTypeEnum.interaction) {
            this.lockInteraction = lockInteraction
      }
    }

    fun describe(): String {
        val assetNames = assets.joinToString { it.name }
        val exitNames = exits.keys.joinToString()
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