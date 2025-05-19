package model

import model.data.Item
import model.data.LockTypeEnum

class Player(var currentRoom: Room) {
    val inventory: MutableList<Item> = mutableListOf()

    fun unlockDoor(direction: String): String {
        val door = currentRoom.exits[direction]
        if (door?.lockType == LockTypeEnum.item) {
            if (inventory.contains(door.lockKey)) {
                return door.unlockDoor(door.lockKey!!)
            } else {
                return "You don't seem to have the right key item on you."
            }
        } else {
            return "This door doesn't seem to have any locks a key can open."
        }
    }

    fun move(direction: String): String {
        val exitDoor = currentRoom.exits[direction]
        val nextRoom = if (exitDoor?.roomA == currentRoom) {
            exitDoor.roomB
        } else {
            exitDoor?.roomA
        }
        if (exitDoor == null) {
            return "You can't go that way."
        }
        if ((exitDoor.lockType == LockTypeEnum.item) && (exitDoor.locked)) {
                return "This door seems to be locked. You need to find and use the key."
        }
        if ((exitDoor.lockType == LockTypeEnum.interaction) && (exitDoor.locked)) {
            return "The door won't budge. It doesn't seem to have a lock. Maybe there's some other way to unlock it."
        } else {
            currentRoom = nextRoom!!
            return("""
            ${exitDoor.openDoor()}
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
            if (asset != null) {
        return asset.interact()
        } else {
            return "Nothing happened."
        }
    }
}