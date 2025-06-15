package de.fridolin1.api.planning.assignedRecipes

import de.fridolin1.models.cateringPlans.AssignedRecipe
import de.fridolin1.models.cateringPlans.MealSlot
import de.fridolin1.models.cooking.Recipe
import de.fridolin1.models.responses.Message
import de.fridolin1.models.responses.MessageStatus
import de.fridolin1.models.snippets.AssignedRecipeCreateObject
import de.fridolin1.modules.DatabaseManager
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.createAssignedRecipes() {
    post {
        val assignedRecipeData = call.receive<AssignedRecipeCreateObject>()
        DatabaseManager.query {
            val assignedRecipe = AssignedRecipe.new {
                this.recipe = Recipe[assignedRecipeData.recipe]
                this.mealSlot = MealSlot[assignedRecipeData.mealSlot]
                this.date = assignedRecipeData.data
                this.personCount = assignedRecipeData.personCount
            }
            call.respond(Message(MessageStatus.SUCCESS, assignedRecipe))
        }
    }
}