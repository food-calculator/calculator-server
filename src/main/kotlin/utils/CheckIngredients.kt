package de.fridolin1.utils

import de.fridolin1.models.cooking.Ingredient
import de.fridolin1.models.snippets.RecipeIngredientSnippet
import de.fridolin1.modules.DatabaseManager

suspend fun checkIngredients(ingredients: List<RecipeIngredientSnippet>): Boolean {
    var isEmpty = false
    DatabaseManager.query {
        val existingIngredientIDs = Ingredient.all().map { it.id.value }
        isEmpty = ingredients.map { it.ingredientID }.none { !existingIngredientIDs.contains(it) }
    }
    return isEmpty
}