package model
import model.data.Item
import model.data.LockTypeEnum

class Door (
    var name: String,
    var open: Boolean = false,
    var locked: Boolean = false,
    var lockType: LockTypeEnum = LockTypeEnum.none,
    var lockInteraction: Asset? = null,
    var lockKey: Item? = null,
    var roomA: Room? = null,
    var roomB: Room? = null,
    var lockText: String? = null,
    var direction: String? = null,
) {
    fun reverseDirection(direction: String): String {
        return when (direction.lowercase()) {
            "north" -> "south"
            "south" -> "north"
            "east" -> "west"
            "west" -> "east"
            "up" -> "down"
            "down" -> "up"
            else -> "unknown"
        }
    }

    companion object {
        fun makeDoor(
            name: String,
            roomA: Room,
            roomB: Room,
            direction: String,
            lockType: LockTypeEnum,
            lockInteraction: Asset? = null,
            lockKey: Item? = null,
            lockText: String? = null,
        ): Door {
            val door = Door(name = name)
            door.roomA = roomA
            door.roomB = roomB
            door.lockType = lockType
            door.direction = direction
            door.lockText = lockText

            when (lockType) {
                LockTypeEnum.item -> {
                    door.lockKey = lockKey
                    door.lockInteraction = null
                    door.locked = true
                }

                LockTypeEnum.interaction -> {
                    door.lockInteraction = lockInteraction
                    door.lockKey = null
                    door.locked = true
                    lockInteraction?.addDependants(door)
                }

                LockTypeEnum.none -> {
                    door.lockInteraction = null
                    door.lockKey = null
                    door.locked = false
                }
            }
            roomA.connectRoom(direction, door)
            roomB.connectRoom(door.reverseDirection(direction), door)

            return door
        }
    }

    fun openDoor(): String {
        if (locked == false) {
            open = true
            return "You opened the door."
        }
        else return "The door won't budge."
    }

    fun unlockDoor(key: Item): String {
        if (key == lockKey) {
            locked = false
            return "You unlocked the door with the ${key.name}"
        } else return "${key.name} could not open this door."
    }

    fun readInteraction(asset: Asset) {
        if ((asset == lockInteraction) && asset.active) {
            locked = false
        }
    }
}