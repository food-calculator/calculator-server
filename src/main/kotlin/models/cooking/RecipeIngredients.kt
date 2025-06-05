package de.fridolin1.models.cooking

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

class RecipeIngredient(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<RecipeIngredient>(RecipeIngredients)

    val recipe by Recipe referencedOn RecipeIngredients.recipe
    val ingredient by Ingredient referencedOn RecipeIngredients.ingredient
    val quantity by RecipeIngredients.quantity
    val unit by RecipeIngredients.unit
}

object RecipeIngredients : IntIdTable("recipe_ingredients") {
    val recipe = reference("recipe", Recipes)
    val ingredient = reference("ingredient", Ingredients)
    val quantity = integer("quantity")
    val unit = varchar("unit", 64)
}

@Serializable
data class RecipeIngredientDTO(
    val id: Int,
    val ingredient: IngredientDTO,
    val quantity: Int,
    val unit: String
)

fun RecipeIngredient.toDTO(): RecipeIngredientDTO {
    return RecipeIngredientDTO(
        this.id.value,
        this.ingredient.toDTO(),
        this.quantity,
        this.unit
    )
}