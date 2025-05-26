package model

class Event (
    var name: String,
    var active: Boolean? = false,
    var activationText: String = "",
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

    private fun activateEvent() {
        this.active = true
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
