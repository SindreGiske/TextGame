package model
import model.data.Interaction
import model.data.Item

class Asset(
    val name: String,
    val description: String,
    var active: Boolean = false
) {
    private val items: MutableList<Item> = mutableListOf()
    var interaction: Interaction? = null
    var Event: Event? = null
    var interactionDependants: MutableList<Door> = mutableListOf()

    fun addItem(item: Item) {
        if (interaction != null) {
            throw IllegalStateException("Cannot add items to interactable assets.")
        }
        items.add(item)
    }

    fun setInteractions(interaction: Interaction) {
        if (items.isNotEmpty()) {
            throw IllegalArgumentException("Cannot add interaction to asset with items.")
        } else if (this.interaction != null) {
            throw IllegalArgumentException("Asset already has interaction.")
        } else
        this.interaction = interaction
    }

    fun updateEvent(event: Event) {
        Event = event
    }

    fun addDependants(door: Door) {
        interactionDependants.add(door)
    }

    fun inspect(): String {
        val lines = mutableListOf<String>()
        lines.add(description)

        if (interaction != null) {
            lines.add("You may be able to interact with the $name.")
        } else if (items.isNotEmpty()) {
            val itemDescriptions = items.joinToString(" ") {
                "a ${it.name.lowercase()}. ${it.description}"
            }
            lines.add("On the $name there is $itemDescriptions")
        } else {
            lines.add("There is nothing special about the $name.")
        }
        return lines.joinToString("\n")
    }

    fun interact(): String {
        if (interaction != null && !interaction!!.itemActivated) {
            this.active = true
            val dependant = interactionDependants.find {it.lockInteraction == this}
            dependant?.readInteraction(this)
            return interaction!!.activation
        } else if (interaction!!.itemActivated) {
            return "Nothing happends. Maybe you can use an item on it?"
        }
        else return "You can not interact with $name."
    }

    fun useItem(item: Item): String {
        val returnText = "You use ${item.name} to ${interaction!!.activation} on ${this.name}"
        if (interaction != null && interaction!!.itemActivated) {
            if (item == interaction!!.neededItem) {
                this.active = true
                if (Event != null) {
                    Event!!.updateEvent()
                    if (Event!!.active) {
                        return ("""
                            $returnText
                            ${Event!!.updateEvent()}
                            
                        """.trimIndent())

                    }
                    return (returnText)
                }
                return (returnText)

            }
            else return ("You can not use ${item.name} on ${this.name}")
        }
        else return ("You can not use ${item.name} on ${this.name}")
    }

    fun takeItem(itemName: String): Item? {
        val item = items.find { it.name.equals(itemName, ignoreCase = true) }
        if (item != null) items.remove(item)
        return item
    }

    fun hasItem(itemName: String): Boolean {
        return items.any { it.name.equals(itemName, ignoreCase = true) }
    }
}