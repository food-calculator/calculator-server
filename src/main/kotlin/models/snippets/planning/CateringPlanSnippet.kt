package de.fridolin1.models.snippets.planning

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class CateringPlanCObject(
    val name: String,
    val defaultPersonCount: Int,
    val dateStart: LocalDate,
    val dateEnd: LocalDate,
)

@Serializable
data class CateringPlanRUObject(
    val id: Int,
    val name: String,
    val defaultPersonCount: Int,
    val mealSlots: List<MealSlotRUObject>,
    val days: List<CateringDayRUObject>,
)