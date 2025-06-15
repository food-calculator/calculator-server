package de.fridolin1.models.snippets

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class AssignedRecipeCreateObject(
    val mealSlot: Int,
    val data: LocalDate,
    val recipe: Int,
    val personCount: Int,
)