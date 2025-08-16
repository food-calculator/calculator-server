package de.fridolin1.models.snippets.planning

import kotlinx.datetime.LocalTime
import kotlinx.serialization.Serializable

@Serializable
data class MealSlotCObject(
    val name: String,
    val cateringPlan: Int,
    val time: LocalTime,
)

@Serializable
data class MealSlotRUObject(
    val id: Int,
    val name: String,
    val time: LocalTime
)