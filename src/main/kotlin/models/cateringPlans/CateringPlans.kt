package de.fridolin1.models.cateringPlans

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

class CateringPlan(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CateringPlan>(CateringPlans)

    var name by CateringPlans.name
    var dateStart by CateringPlans.dateStart
    var dateEnd by CateringPlans.dateEnd
    var defaultPersonCount by CateringPlans.defaultPersonCount
    val mealSlots by MealSlot referrersOn MealSlots.cateringPlan
}

object CateringPlans : IntIdTable() {
    val name = varchar("name", 255)
    val dateStart = date("dateStart")
    val dateEnd = date("dateEnd")
    val defaultPersonCount = integer("defaultPersonCount")
}

@Serializable
data class CateringPlanDTO(
    val id: Int,
    val name: String,
    val dateStart: LocalDate,
    val dateEnd: LocalDate,
    val defaultPersonCount: Int,
    val mealSlots: List<MealSlotsDTO>,
)

fun CateringPlan.toDTO(): CateringPlanDTO {
    return CateringPlanDTO(
        this.id.value,
        this.name,
        this.dateStart,
        this.dateEnd,
        this.defaultPersonCount,
        this.mealSlots.map { it.toDTO() }
    )
}