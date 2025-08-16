package de.fridolin1.models.snippets.planning

import kotlinx.serialization.Serializable

@Serializable
data class AssignedRecipeCObject(
    val mealSlot: Int,
    val day: Int,
    val recipe: Int,
    val personCount: Int,
)

@Serializable
data class AssignedRecipeRUObject(
    val id: Int,
    val day: Int,
    val recipe: Int,
    val personCount: Int,
    val mealSlot: Int
)