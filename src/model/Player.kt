package model

import model.data.Item
import model.data.LockTypeEnum

class Player(var currentRoom: Room) {
    val inventory: MutableList<Item> = mutableListOf()

    fun move(direction: String): String {
        val nextRoom = currentRoom.exits[direction]
        if (nextRoom == null) {
            return "You can't go that way."
        }
        if (nextRoom.lockType == LockTypeEnum.item) {
            if (inventory.contains(nextRoom.lockKey)) {
                nextRoom.updateLockType(LockTypeEnum.none, null)
                return "You used the ${nextRoom.lockKey!!.name} to open ${nextRoom.name}. Do you wish to walk through?"
            } else {
                return "This door seems to be locked. You need to find the key."
            }
        }
        if ((nextRoom.lockType == LockTypeEnum.interaction) && (!nextRoom.lockInteraction!!.active)) {
            return "The door won't budge. It doesn't seem to have a lock. Maybe there's some other way to unlock it."
        } else {
            val previousRoom = currentRoom
            currentRoom = nextRoom
            return("""
            ${previousRoom.exitText}
            You move $direction into ${currentRoom.name}.
            ${currentRoom.describe()}
            """.trimIndent())
        }
    }

    fun inspectAsset(assetName: String): String {
        val asset = currentRoom.findAsset(assetName)
        return asset?.inspect() ?: "You don't see any $assetName here to inspect."
    }

    fun takeItemFromAsset(itemName: String, assetName: String): String {
        val asset = currentRoom.findAsset(assetName)
        if (asset != null && asset.hasItem(itemName)) {
            val item = asset.takeItem(itemName)
            if (item != null) {
                inventory.add(item)
                return "You took the $itemName from the $assetName"
            }
        }
        return "Couldn't find $itemName on $assetName"
    }

    fun checkInventory(): String {
        return if (inventory.isEmpty()) "Your inventory is empty."
        else inventory.joinToString("\n") { "${it.name}: ${it.description}" }
    }

    fun interact(assetName: String): String {
        val asset = currentRoom.findAsset(assetName)
        return asset!!.interact()
    }

}