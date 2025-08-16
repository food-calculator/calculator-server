package de.fridolin1.api.planning.assignedRecipes.list

import de.fridolin1.models.cateringPlans.AssignedRecipe
import de.fridolin1.models.cateringPlans.AssignedRecipesDTO
import de.fridolin1.models.cateringPlans.MealSlot
import de.fridolin1.models.cateringPlans.toDTO
import de.fridolin1.models.cooking.Recipe
import de.fridolin1.models.responses.Message
import de.fridolin1.models.responses.MessageStatus
import de.fridolin1.models.snippets.planning.AssignedRecipeCObject
import de.fridolin1.modules.DatabaseManager
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.createAssignedRecipeList() {
    post {
        val assignedRecipes = call.receive<List<AssignedRecipeCObject>>()
        DatabaseManager.query {
            val recipeList = mutableListOf<AssignedRecipesDTO>()
            assignedRecipes.forEach {
                val recipe = AssignedRecipe.new {
                    this.date = it.date
                    this.personCount = it.personCount
                    this.recipe = Recipe[it.recipe]
                    this.mealSlot = MealSlot[it.mealSlot]
                }
                recipeList.add(recipe.toDTO())
            }
            call.respond(Message(MessageStatus.SUCCESS, recipeList))
        }
    }
}