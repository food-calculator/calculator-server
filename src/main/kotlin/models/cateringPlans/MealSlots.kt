package de.fridolin1.models.cateringPlans

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.time

class MealSlot(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MealSlot>(MealSlots)

    var name by MealSlots.name
    var cateringPlan by CateringPlan referencedOn MealSlots.cateringPlan
    var time by MealSlots.time
    val assignedRecipes by AssignedRecipe referrersOn AssignedRecipes.mealSlot
}

object MealSlots : IntIdTable() {
    val cateringPlan = reference("cateringPlan", CateringPlans)
    val name = varchar("name", 255)
    val time = time("time")
    val defaultPersonCount = integer("defaultPersonCount")
}
