package de.fridolin1.models.cateringPlans

import de.fridolin1.models.cooking.Recipe
import de.fridolin1.models.cooking.Recipes
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

class AssignedRecipe(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AssignedRecipe>(AssignedRecipes)

    var day by AssignedRecipes.day
    var recipe by Recipe referencedOn AssignedRecipes.recipe
    var personCount by AssignedRecipes.personCount
    var mealSlot by MealSlot referencedOn AssignedRecipes.mealSlot
}

object AssignedRecipes : IntIdTable() {
    val mealSlot = reference("mealSlot", MealSlots)
    val day = reference("day", CateringDays)
    val recipe = reference("recipe", Recipes)
    val personCount = integer("personCount")
}
