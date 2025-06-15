package de.fridolin1.api.planning.assignedRecipes

import de.fridolin1.models.cateringPlans.AssignedRecipe
import de.fridolin1.models.cateringPlans.AssignedRecipesDTO
import de.fridolin1.models.cooking.Recipe
import de.fridolin1.models.responses.Message
import de.fridolin1.models.responses.MessageStatus
import de.fridolin1.modules.DatabaseManager
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.updateAssignedRecipes() {
    put {
        val assignedRecipeData = call.receive<AssignedRecipesDTO>()
        DatabaseManager.query {
            val assignedRecipe = AssignedRecipe[assignedRecipeData.id]
            assignedRecipe.recipe = Recipe[assignedRecipeData.recipe]
            assignedRecipe.personCount = assignedRecipeData.personCount
            assignedRecipe.date = assignedRecipeData.data
            call.respond(Message(MessageStatus.SUCCESS, "Updated assignedRecipe!"))
        }
    }
}