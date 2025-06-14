package de.fridolin1.api.planning.cateringPlans

import de.fridolin1.models.cateringPlans.CateringPlan
import de.fridolin1.models.snippets.toPlanHead
import de.fridolin1.modules.DatabaseManager
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.listCateringPlans() {
    get {
        DatabaseManager.query {
            call.respond(CateringPlan.all().map { it.toPlanHead() })
        }
    }
}