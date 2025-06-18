package de.fridolin1.api.planning.assignedRecipes.list

import de.fridolin1.models.cateringPlans.AssignedRecipe
import de.fridolin1.models.responses.Message
import de.fridolin1.models.responses.MessageStatus
import de.fridolin1.modules.DatabaseManager
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteAssignedRecipeList() {
    delete {
        val ids = call.receive<List<Int>>()
        DatabaseManager.query {
            ids.forEach {
                AssignedRecipe[it].delete()
            }
            call.respond(Message(MessageStatus.SUCCESS, "Successfully deleted elements"))
        }
    }
}