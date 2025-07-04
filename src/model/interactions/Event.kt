package model.interactions

import model.Asset
import model.Door
import model.Room
import nextLevel

class Event (
    var name: String,
    var active: Boolean? = false,
    var activationText: String = "",
    var endEvent: Boolean? = false,
    var entityEventRoom: Room? = null,
) {
    var assetList = mutableListOf<Asset>()
    var doorList = mutableListOf<Door>()

    fun addAsset(asset: Asset) {
        assetList.add(asset)
        asset.setEvent(this)
    }

    fun addDoor(door: Door) {
        doorList.add(door)
    }

    fun npcActivateEvent() {
        activateEvent()
    }

    private fun activateEvent() {
        this.active = true
        if (entityEventRoom != null && entityEventRoom!!.npc!!.leavesAfterDialogue) {
            entityEventRoom?.npcLeaves()
        }
        if (endEvent == true) {
            nextLevel()
        }
        if (doorList.isEmpty() && entityEventRoom != null) {
            entityEventRoom?.updateDescription()
        } else
        doorList.forEach {
            it.hidden = false
            it.locked = false
            it.open = true
            if (it.lockText != "") {
                it.roomA!!.updateDescription()
                println(activationText)
            }
        }
    }

    fun updateEvent(asset: Asset) {
        val itsAssetList = assetList
        val itsAsset = itsAssetList.find { it.name == asset.name }

        itsAsset!!.active = true
        asset.active = true
        if (assetList.contains(asset)) {
            if (assetList.all { it.active }) {
                activateEvent()
            }
        }
    }
}
