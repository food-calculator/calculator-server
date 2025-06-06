package de.fridolin1.modules

import de.fridolin1.models.cooking.Ingredients
import de.fridolin1.models.cooking.RecipeIngredients
import de.fridolin1.models.cooking.Recipes
import de.fridolin1.models.images.Images
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.DatabaseConfig
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File

object DatabaseManager {
    val db = File("./databases/FoodCalculator")
    val database: Database

    init {
        println("Database initialization...")

        if (!db.parentFile.exists()) db.parentFile.mkdir()
//        if (!db.exists()) db.createNewFile()
        database = Database.connect(
            url = "jdbc:h2:${db.path}",
            driver = "org.h2.Driver",
            databaseConfig = DatabaseConfig {
                keepLoadedReferencesOutOfTransaction = true
            }
        )

        transaction {
            SchemaUtils.create(Recipes, Ingredients, RecipeIngredients)
            SchemaUtils.create(Images)
        }

        println("Database initialization complete!")
    }

    suspend fun <T> query(block: suspend Transaction.() -> T): T {
        return newSuspendedTransaction(Dispatchers.IO, database) { block() }
    }
}
