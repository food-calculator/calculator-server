package de.fridolin1.models.cateringPlans

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

class CateringPlan(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CateringPlan>(CateringPlans)

    var name by CateringPlans.name
    var defaultPersonCount by CateringPlans.defaultPersonCount

    val slots by MealSlot referrersOn MealSlots.cateringPlan
    val days by CateringDay referrersOn CateringDays.plan
}

object CateringPlans : IntIdTable() {
    val name = varchar("name", 255)
    val dateStart = date("dateStart")
    val defaultPersonCount = integer("defaultPersonCount")
}
