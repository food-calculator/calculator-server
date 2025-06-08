package de.fridolin1.models.snippets

import de.fridolin1.models.cooking.RecipeIngredient
import kotlinx.serialization.Serializable

@Serializable
data class RecipeIngredientSnippet(
    val ingredientID: Int,
    val quantity: Double
)

fun RecipeIngredient.toSnippet(): RecipeIngredientSnippet {
    return RecipeIngredientSnippet(
        this.ingredient.id.value,
        this.quantity
    )
}