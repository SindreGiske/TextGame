package model

import model.data.Item
import model.data.CraftingRecipe
import model.util.RecipeBook
import model.util.clearConsole

fun CraftingMenu(playerInventory: List<Item>):Item {
    fun knownRecipes(inventory: List<Item>): List<CraftingRecipe> {
        val recipeNames = inventory.map { it.name }.toSet()
        return RecipeBook.recipes.filter { recipeNames.contains(it.recipeName) }
    }

    val inventory: List<Item> = playerInventory
    var recipes: List<CraftingRecipe> = knownRecipes(inventory)
    val itemNames = inventory.filterNot { it.name.contains("recipe", ignoreCase = true) }.toString().toMutableList()

    val craftingHeader: String = ("""
            ------------------------
                 CRAFTING MENU
            ------------------------
            
        """.trimIndent())
    fun listRecipes() {
        recipes.forEach { println(it.makes) }
    }
    clearConsole()
    println(craftingHeader)
    listRecipes()

    while (true) {








    }

}
