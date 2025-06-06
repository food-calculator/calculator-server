package de.fridolin1.models.cooking

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

class Recipe(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Recipe>(Recipes)

    var name by Recipes.name
    var description by Recipes.description
    var timeExpenditure by Recipes.timeExpenditure
    var minimumAge by Recipes.minimumAge
    var portionSize by Recipes.portionSize
    val recipesIngredients by RecipeIngredient referrersOn RecipeIngredients.recipe
}

object Recipes : IntIdTable("Recipes") {
    val name = varchar("name", 64)
    val description = text("description")
    val timeExpenditure = varchar("timeExpenditure", 64)
    val minimumAge = integer("minimunAge")
    val portionSize = integer("portionSize")
}

@Serializable
data class RecipeDTO(
    val id: Int,
    val name: String,
    val description: String,
    val timeExpenditure: String,
    val minimumAge: Int,
    val portionSize: Int,
    val recipesIngredients: List<RecipeIngredientDTO>
)

fun Recipe.toDTO(): RecipeDTO {
    return RecipeDTO(
        this.id.value,
        this.name,
        this.description,
        this.timeExpenditure,
        this.minimumAge,
        this.portionSize,
        this.recipesIngredients.map { it.toDTO() }
    )
}