package de.fridolin1.api.planning.mealSlots

import de.fridolin1.models.cateringPlans.MealSlot
import de.fridolin1.models.responses.Message
import de.fridolin1.models.responses.MessageStatus
import de.fridolin1.models.snippets.MealSlotHead
import de.fridolin1.modules.DatabaseManager
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.put

fun Route.updateMealSlot() {
    put {
        val mealSlotHead = call.receive<MealSlotHead>()
        DatabaseManager.query {
            val mealSlot = MealSlot[mealSlotHead.id]
            if (mealSlot.cateringPlan.id.value != mealSlotHead.cateringPlan) {
                call.respond(Message(MessageStatus.ERROR, "Cant change catering plan"))
                return@query
            }
            mealSlot.name = mealSlotHead.name
            mealSlot.time = mealSlotHead.time
            mealSlot.defaultPersonCount = mealSlotHead.defaultPersonCount
            call.respond(Message(MessageStatus.SUCCESS, "Successfully updated meal slot"))
        }
    }
}