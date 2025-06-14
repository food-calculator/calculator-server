package de.fridolin1.api.planning.mealSlots

import de.fridolin1.models.cateringPlans.CateringPlan
import de.fridolin1.models.cateringPlans.MealSlot
import de.fridolin1.models.responses.Message
import de.fridolin1.models.responses.MessageStatus
import de.fridolin1.models.snippets.MealSlotHead
import de.fridolin1.models.snippets.toMealSlotHead
import de.fridolin1.modules.DatabaseManager
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.createMealSlot() {
    post {
        val mealSlotHead = call.receive<MealSlotHead>()
        val slot = call.receive<MealSlotHead>()
        DatabaseManager.query {
            val cateringPlan = CateringPlan[slot.cateringPlan]
            val mealSlot = MealSlot.new {
                this.cateringPlan = cateringPlan
                this.name = mealSlotHead.name
                this.time = mealSlotHead.time
                this.defaultPersonCount = mealSlotHead.defaultPersonCount
            }
            call.respond(Message(MessageStatus.SUCCESS, mealSlot.toMealSlotHead()))
        }
    }
}