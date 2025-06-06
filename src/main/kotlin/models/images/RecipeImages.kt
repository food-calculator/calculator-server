package de.fridolin1.models.images

import de.fridolin1.models.cooking.Recipe
import de.fridolin1.models.cooking.Recipes
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

class RecipeImage(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<RecipeImage>(RecipeImages)

    var image by Image referencedOn RecipeImages.image
    var recipe by Recipe referencedOn RecipeImages.recipe
}

object RecipeImages : IntIdTable() {
    val image = reference("image", Images)
    val recipe = reference("recipe", Recipes)
}