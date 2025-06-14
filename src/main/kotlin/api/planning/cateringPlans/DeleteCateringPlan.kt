package de.fridolin1.api.planning.cateringPlans

import de.fridolin1.models.cateringPlans.CateringPlan
import de.fridolin1.models.responses.Message
import de.fridolin1.models.responses.MessageStatus
import de.fridolin1.modules.DatabaseManager
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteCateringPlan() {
    delete {
        val id = call.receiveParameters()["id"]?.toInt()!!
        DatabaseManager.query {
            CateringPlan[id].delete()
            call.respond(Message(MessageStatus.SUCCESS, "Successfully deleted catering plan"))
        }
    }
}