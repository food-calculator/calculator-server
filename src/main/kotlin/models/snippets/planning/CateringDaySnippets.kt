package de.fridolin1.models.snippets.planning

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class CateringDayCObject(
    val plan: Int,
    val defaultPersonCount: Int,
    val day: LocalDate
)

@Serializable
data class CateringDayRUObject(
    val id: Int,
    val defaultPersonCount: Int,
    val day: LocalDate,
)