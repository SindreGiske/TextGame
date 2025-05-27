package model
import model.data.Item
import model.interactions.Event

class Asset(
    val name: String,
    val description: String,
    var active: Boolean = false,
    var interaction: String? = "",
    var interactionItem: Item? = null,
) {
    private val items: MutableList<Item> = mutableListOf()
    private var interactionDependants: MutableList<Door> = mutableListOf()
    private var event: Event? = null

    fun setEvent(event: Event) {
        this.event = event
    }

    fun addItem(item: Item) {
        if (interaction != "") {
            throw IllegalStateException("Cannot add items to interactable assets.")
        }
        items.add(item)
    }

    fun addDependants(door: Door) {
        interactionDependants.add(door)
    }

    fun inspect(): String {
        val lines = mutableListOf<String>()
        lines.add(description)

        if (interaction != "") {
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
        if (interaction != "" && interactionItem == null) {
            this.active = true
            val dependant = interactionDependants.find {it.lockInteraction == this}
            dependant?.readInteraction(this)
            if (event != null) {
                event!!.updateEvent(this)
            }
            return """
                
        ${interaction!!}
                
            """.trimMargin()
        } else if (interactionItem != null) {
            return "Looks like you have to use an item on $name"
        } else
        return "You can not interact with $name."
    }

    fun useItemOn(item: Item):String {
        if (interactionItem != null) {
            if (interactionItem == item) {
                event!!.updateEvent(this)
                this.active = true
                return ("You use ${item.name} to $interaction on the $name.")
            }
            else return "You can't use ${item.name} on $name."
        }
        else return "You can't find any way to use any items on $name"
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