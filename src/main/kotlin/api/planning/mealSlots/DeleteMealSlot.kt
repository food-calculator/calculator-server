package de.fridolin1.api.planning.mealSlots

import de.fridolin1.models.cateringPlans.MealSlot
import de.fridolin1.models.responses.Message
import de.fridolin1.models.responses.MessageStatus
import de.fridolin1.modules.DatabaseManager
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteMealSlot() {
    delete {
        val id = call.receiveParameters()["id"]?.toInt()!!
        DatabaseManager.query {
            val mealSlot = MealSlot[id]
            if (!mealSlot.assignedRecipes.empty()) {
                call.respond(Message(MessageStatus.DEPENDENCIES_NOT_EXISTING, mealSlot.assignedRecipes.map { it.id.value }))
                return@query
            }
            mealSlot.delete()
            call.respond(Message(MessageStatus.SUCCESS, "Successfully deleted meal slot."))
        }
    }
}