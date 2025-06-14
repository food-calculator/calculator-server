package de.fridolin1.models.cooking

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

class RecipeIngredient(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<RecipeIngredient>(RecipeIngredients)

    var recipe by Recipe referencedOn RecipeIngredients.recipe
    var ingredient by Ingredient referencedOn RecipeIngredients.ingredient
    var quantity by RecipeIngredients.quantity
}

object RecipeIngredients : IntIdTable("recipe_ingredients") {
    val recipe = reference("recipe", Recipes)
    val ingredient = reference("ingredient", Ingredients)
    val quantity = double("quantity")
}

@Serializable
data class RecipeIngredientDTO(
    val id: Int,
    val ingredient: IngredientDTO,
    val quantity: Double,
)

fun RecipeIngredient.toDTO(): RecipeIngredientDTO {
    return RecipeIngredientDTO(
        this.id.value,
        this.ingredient.toDTO(),
        this.quantity,
    )
}