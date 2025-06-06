package de.fridolin1.models.cooking

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

class Ingredient(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Ingredient>(Ingredients)

    var name by Ingredients.name
    var unit by Ingredients.unit
}

object Ingredients : IntIdTable("ingredients") {
    val name = varchar("name", 64)
    val unit = varchar("unit", 64)
}

@Serializable
data class IngredientDTO(
    val id: Int,
    val name: String,
    val unit: String
)

fun Ingredient.toDTO(): IngredientDTO {
    return IngredientDTO(
        this.id.value,
        this.name,
        this.unit
    )
}