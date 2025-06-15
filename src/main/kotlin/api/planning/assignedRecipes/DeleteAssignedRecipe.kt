package de.fridolin1.api.planning.assignedRecipes

import de.fridolin1.models.cateringPlans.AssignedRecipe
import de.fridolin1.models.responses.Message
import de.fridolin1.models.responses.MessageStatus
import de.fridolin1.modules.DatabaseManager
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteAssignedRecipes() {
    delete {
        val id = call.receiveParameters()["id"]?.toInt()!!
        DatabaseManager.query {
            val assignedRecipe = AssignedRecipe[id]
            assignedRecipe.delete()
            call.respond(Message(MessageStatus.SUCCESS, "Successfully deleted assigned recipe!"))
        }
    }
}