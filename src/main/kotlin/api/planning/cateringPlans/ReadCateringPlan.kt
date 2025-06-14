package de.fridolin1.api.planning.cateringPlans

import de.fridolin1.models.cateringPlans.CateringPlan
import de.fridolin1.models.cateringPlans.toDTO
import de.fridolin1.models.responses.Message
import de.fridolin1.models.responses.MessageStatus
import de.fridolin1.modules.DatabaseManager
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.readCateringPlan() {
    get {
        val id = call.queryParameters["id"]?.toInt()!!
        DatabaseManager.query {
            call.respond(Message(MessageStatus.SUCCESS, CateringPlan[id].toDTO()))
        }
    }
}