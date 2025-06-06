package de.fridolin1.models.snippets

import de.fridolin1.models.cooking.Recipe
import kotlinx.serialization.Serializable

@Serializable
data class RecipeHead(
    val id: Int,
    val name: String,
    val minimumAge: Int,
    val timeExpenditure: String
)

fun Recipe.toRecipeHead(): RecipeHead = RecipeHead(
    this.id.value,
    this.name,
    this.minimumAge,
    this.timeExpenditure
)

@Serializable
data class RecipeSearch(
    val id: Int,
    val name: String,
    val minimumAge: Int,
    val timeExpenditure: String,
    val ingredientIDs: List<Int>
)

fun Recipe.toRecipeSearchObject(): RecipeSearch = RecipeSearch(
    this.id.value,
    this.name,
    this.minimumAge,
    this.timeExpenditure,
    this.recipesIngredients.map { it.ingredient.id.value }
)

@Serializable
data class RawRecipe(
    val id: Int,
    val name: String,
    val description: String,
    val minimumAge: Int,
    val timeExpenditure: String,
    val portionSize: Int,
    val ingredients: List<RecipeIngredientSnippet>
)

fun Recipe.toRawRecipe(): RawRecipe = RawRecipe(
    this.id.value,
    this.name,
    this.description,
    this.minimumAge,
    this.timeExpenditure,
    this.portionSize,
    this.recipesIngredients.map { it.toSnippet() }
)