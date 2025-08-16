package de.fridolin1.models.cateringPlans

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

class CateringDay(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CateringDay>(CateringDays)

    val plan by CateringPlan referencedOn CateringDays.plan
    val day by CateringDays.day
    val defaultPersonCount by CateringDays.defaultPersonCount
}

object CateringDays : IntIdTable() {
    val plan = reference("cateringPlan", CateringPlans)
    val day = date("date")
    val defaultPersonCount = integer("defaultPersonCount")
}