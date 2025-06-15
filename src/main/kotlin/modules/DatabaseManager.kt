package de.fridolin1.modules

import de.fridolin1.models.cooking.Ingredients
import de.fridolin1.models.cooking.RecipeIngredients
import de.fridolin1.models.cooking.Recipes
import de.fridolin1.models.images.Images
import de.fridolin1.models.images.RecipeImages
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.DatabaseConfig
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.transactions.transactionManager
import java.io.File
import java.sql.Connection

object DatabaseManager {
    val db = File("./data/FoodCalculator.db")
    val database: Database

    init {
        println("Database initialization...")
        println("Database file path: ${db.absolutePath}")

        if (!db.parentFile.exists()) db.parentFile.mkdir()
//        if (!db.exists()) db.createNewFile()
        database = Database.connect(
            url = "jdbc:sqlite:${db.path}",
            driver = "org.sqlite.JDBC",
            databaseConfig = DatabaseConfig {
                keepLoadedReferencesOutOfTransaction = true
            },
        )
        database.transactionManager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE

        transaction {
            SchemaUtils.create(Recipes, Ingredients, RecipeIngredients)
            SchemaUtils.create(Images, RecipeImages)
        }

        println("Database initialization complete!")
    }

    suspend fun <T> query(block: suspend Transaction.() -> T): T {
        return newSuspendedTransaction(Dispatchers.IO, database) { block() }
    }
}
