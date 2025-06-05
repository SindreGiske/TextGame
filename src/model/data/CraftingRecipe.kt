package model.data

data class CraftingRecipe(
    val recipeName: String,
    val description: String,
    val makes: Item,
    val ingredients: List<String>
)
