package de.fridolin1.models.snippets

import de.fridolin1.models.cateringPlans.CateringPlan
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class CateringPlanHead(
    val id: Int,
    val name: String,
    val dateStart: LocalDate,
    val dateEnd: LocalDate,
    val defaultPersonCount: Int,
)

fun CateringPlan.toPlanHead(): CateringPlanHead {
    return CateringPlanHead(
        this.id.value,
        this.name,
        this.dateStart,
        this.dateEnd,
        this.defaultPersonCount,
    )
}