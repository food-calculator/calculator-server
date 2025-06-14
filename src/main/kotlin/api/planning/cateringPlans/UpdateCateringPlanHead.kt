package de.fridolin1.api.planning.cateringPlans

import de.fridolin1.models.cateringPlans.CateringPlan
import de.fridolin1.models.responses.Message
import de.fridolin1.models.responses.MessageStatus
import de.fridolin1.models.snippets.CateringPlanHead
import de.fridolin1.modules.DatabaseManager
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.updateCateringPlanHead() {
    put {
        val cateringPlanHead = call.receive<CateringPlanHead>()
        DatabaseManager.query {
            val cateringPlan = CateringPlan[cateringPlanHead.id]
            cateringPlan.name = cateringPlanHead.name
            cateringPlan.dateStart = cateringPlanHead.dateStart
            cateringPlan.dateEnd = cateringPlanHead.dateEnd
            cateringPlan.defaultPersonCount = cateringPlanHead.defaultPersonCount
            call.respond(Message(MessageStatus.SUCCESS, "Successfully updated Plan"))
        }
    }
}