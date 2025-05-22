package model

class Event (
    var name: String,
    var interactionAssets: MutableList<Asset> = mutableListOf(),
    var dependants: MutableList<Door> = mutableListOf(),
    var active: Boolean = false,
    var willUpdateDescription: Boolean = false,
    var newDescription: String = ""
) {
    fun makeMultiEvent(name: String) {
        this.name = name
    }
    fun addAsset(asset: Asset) {
        interactionAssets.add(asset)
    }

    fun addDependant(dependant: Door) {
        dependants.add(dependant)
    }

    fun updateEvent(): String? {
        if (interactionAssets.all { it.active }) {
            active = true
            dependants.forEach { dependant ->
                dependant.readMultiInteraction(this)
            }
        }

        if (willUpdateDescription) {
            dependants.forEach { dependant ->
                dependant.roomA?.updateDescription(this.newDescription)
            }
            dependants.forEach { dependant ->
                dependant.roomA?.describe()
            }
        }

        return null
    }
}