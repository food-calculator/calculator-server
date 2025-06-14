package de.fridolin1.models.cateringPlans

import kotlinx.datetime.LocalTime
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
    var defaultPersonCount by MealSlots.defaultPersonCount
    val assignedRecipes by AssignedRecipe referrersOn AssignedRecipes.mealSlot
}

object MealSlots : IntIdTable() {
    val cateringPlan = reference("cateringPlan", CateringPlans)
    val name = varchar("name", 255)
    val time = time("time")
    val defaultPersonCount = integer("defaultPersonCount")
}

data class MealSlotsDTO(
    val id: Int,
    val name: String,
    val time: LocalTime,
    val defaultPersonCount: Int,
    val assignedRecipes: List<AssignedRecipesDTO>,
)

fun MealSlot.toDTO(): MealSlotsDTO {
    return MealSlotsDTO(
        this.id.value,
        this.name,
        this.time,
        this.defaultPersonCount,
        this.assignedRecipes.map { it.toDTO() },
    )
}