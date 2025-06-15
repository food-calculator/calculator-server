package de.fridolin1.models.cateringPlans

import de.fridolin1.models.cooking.Recipe
import de.fridolin1.models.cooking.Recipes
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

class AssignedRecipe(id: EntityID<Int>) : IntEntity(id) {
    companion object: IntEntityClass<AssignedRecipe>(AssignedRecipes)

    var date by AssignedRecipes.date
    var recipe by Recipe referencedOn AssignedRecipes.recipe
    var personCount by AssignedRecipes.personCount
    var mealSlot  by MealSlot referencedOn AssignedRecipes.mealSlot
}

object AssignedRecipes : IntIdTable() {
    val mealSlot = reference("mealSlot", MealSlots)
    val date = date("date")
    val recipe = reference("recipe", Recipes)
    val personCount = integer("personCount")
}

@Serializable
data class AssignedRecipesDTO(
    val id: Int,
    val data: LocalDate,
    val recipe: Int,
    val personCount: Int,
)

fun AssignedRecipe.toDTO(): AssignedRecipesDTO {
    return AssignedRecipesDTO(
        this.id.value,
        this.date,
        this.recipe.id.value,
        this.personCount
    )
}