package de.fridolin1.models.snippets

import de.fridolin1.models.cateringPlans.MealSlot
import kotlinx.datetime.LocalTime
import kotlinx.serialization.Serializable

@Serializable
data class MealSlotHead(
    val id: Int,
    val cateringPlan: Int,
    val name: String,
    val time: LocalTime,
    val defaultPersonCount: Int
)

fun MealSlot.toMealSlotHead(): MealSlotHead {
    return MealSlotHead(
        this.id.value,
        this.cateringPlan.id.value,
        this.name,
        this.time,
        this.defaultPersonCount
    )
}