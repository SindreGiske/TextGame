package model

import model.data.Item
import model.data.LockTypeEnum
import model.interactions.runDialogue
import model.util.reverseDirection

class Player(var currentRoom: Room) {
    val inventory: MutableList<Item> = mutableListOf()
    val roomMovementDirList: MutableList<String> = mutableListOf()
    val roomMovementDoorList: MutableList<Door> = mutableListOf()
    val roomMovementRoomList: MutableList<Room> = mutableListOf()
    var assetCache: MutableList<Asset> = mutableListOf()

    fun unlockDoor(direction: String?): String {
        var directionName = direction!!.removeSuffix("door").removePrefix("door").trim()
        println("unlockDoor directionName : $directionName")
        var door: Door? = null
        door = if (currentRoom.exits.size == 1) {
            currentRoom.exits.values.first()
        } else {
            currentRoom.exits[directionName]
        }
        if (currentRoom.exits.size > 1 && directionName == "") {
            return "Which door do you want to unlock?"
        }

        return if (door?.lockType == LockTypeEnum.item) {
            if (inventory.contains(door.lockKey)) {
                door.unlockDoor(door.lockKey!!)
            } else {
                "You don't seem to have the right key item on you."
            }
        } else {
            "This door doesn't seem to have any locks a key can open."
        }
    }

    fun move(direction: String): String {
        if (direction == "back") {
            val backDirection: String = roomMovementDirList.last().reverseDirection()
            val nextRoom = roomMovementRoomList.last()
            val backDoor = roomMovementDoorList.last()

            return(moveNextRoom(nextRoom, backDoor, backDirection))
        }
        val exitDoor = currentRoom.exits[direction]
        val nextRoom = if (exitDoor?.roomA == currentRoom) {
            exitDoor.roomB
        } else {
            exitDoor?.roomA
        }
        if (exitDoor == null) {
            return "You can't go that way."
        }
        if (exitDoor.isHidden()) {
            return "You can't go that way."
        }


        if ((exitDoor.lockType == LockTypeEnum.item) && (exitDoor.locked)) {
            return "This door seems to be locked. You need to find and use the key."
        }
        return if ((exitDoor.lockType == LockTypeEnum.interaction) && (exitDoor.locked)) {
            "The door won't budge. It doesn't seem to have a lock. Maybe there's some other way to unlock it."
        } else {
            (moveNextRoom(nextRoom!!, exitDoor, direction))
        }
    }
    fun moveNextRoom(nextRoom: Room, exitDoor: Door, direction: String): String {
        assetCache = mutableListOf()
        roomMovementDirList.add(direction)
        roomMovementDoorList.add(exitDoor)
        roomMovementRoomList.add(currentRoom)

        currentRoom = nextRoom
        return("""
            ${exitDoor.openDoor()}
            You move $direction into ${currentRoom.name}.
            ${currentRoom.describe()}
            """.trimIndent())
    }

    fun inspectAsset(assetName: String): String {
        val asset = currentRoom.findAsset(assetName)
        if (asset == null) {
            return "couldn't find asset $assetName."
        }
        assetCache.add(asset)
        return asset.inspect()
    }

    fun inspectDoor(direction: String): String {
        val door = currentRoom.exits[direction]
        return door?.describeDoor() ?: "there isn't a door there."
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

    fun takeItemFromAssetCache(itemName: String): String {
        for (i in assetCache) {
            if (i.hasItem(itemName)) {
                return takeItemFromAsset(itemName, i.name)
                break
            }
        }
        return ("Where do you want to take the $itemName from?")
    }

    fun initiateDialogue() {
        if (currentRoom.npc != null) {
            runDialogue(currentRoom.npc!!)
        } else {
            println("You seem to be all alone.")
        }
    }

    fun useItemOnAsset(itemName: String, assetName: String): String {
        val item = inventory.find { it.name == itemName }
        val asset = currentRoom.findAsset(assetName)
        return if (item != null) {
            if (asset != null) {
                asset.useItemOn(item)
            } else if (assetName == "door") {
                return (unlockDoor(""))
            } else "Couldn't find $assetName in ${currentRoom.name}."
        } else "You couldn't find $itemName in your inventory."
    }

    fun craft() {
        inventory.add(CraftingMenu(inventory))
    }

    fun checkInventory(): String {
        return if (inventory.isEmpty()) "Your inventory is empty."
        else inventory.joinToString("\n") { "${it.name}: ${it.description}" }
    }

    fun interact(assetName: String): String {
        val asset = currentRoom.findAsset(assetName)
        return if (asset != null) {
            asset.interact()
        } else {
            "Nothing happened."
        }
    }
}