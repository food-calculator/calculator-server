package de.fridolin1.api.planning.cateringPlans

import de.fridolin1.models.cateringPlans.CateringPlan
import de.fridolin1.models.responses.Message
import de.fridolin1.models.responses.MessageStatus
import de.fridolin1.models.snippets.CateringPlanHead
import de.fridolin1.models.snippets.toPlanHead
import de.fridolin1.modules.DatabaseManager
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.createCateringPlan() {
    post {
        val planHead = call.receive<CateringPlanHead>()
        DatabaseManager.query {
            val plan = CateringPlan.new {
                this.name = planHead.name
                this.dateStart = planHead.dateStart
                this.dateEnd = planHead.dateEnd
                this.defaultPersonCount = planHead.defaultPersonCount
            }
            call.respond(Message(MessageStatus.SUCCESS, plan.toPlanHead()))
        }
    }
}