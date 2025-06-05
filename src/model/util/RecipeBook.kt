package model.util

import model.data.CraftingRecipe
import model.data.Item

object RecipeBook {
    val recipes = listOf(
        CraftingRecipe(
            recipeName = "",
            description = "",
            makes = Item(name = "", description = ""),
            ingredients = listOf("", "", "")
        )
    )
}