package de.fridolin1.modules

import com.mongodb.kotlin.client.coroutine.MongoClient
import io.github.flaxoos.ktor.server.plugins.taskscheduling.*
import io.github.flaxoos.ktor.server.plugins.taskscheduling.managers.lock.database.*
import io.github.flaxoos.ktor.server.plugins.taskscheduling.managers.lock.redis.*
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureAdministration() {
    install(TaskScheduling) {
        // Choose task manager config based on your chosen task manager dependencies
        redis { // <-- given no name, this will be the default manager
            connectionPoolInitialSize = 1
            host = "host"
            port = 6379
            username = "my_username"
            password = "my_password"
            connectionAcquisitionTimeoutMs = 1_000
            lockExpirationMs = 60_000
        }
        jdbc("my jdbc manager") { // <-- given a name, a manager can be explicitly selected for a task
            database = Database.connect(
                url = "jdbc:postgresql://host:port",
                driver = "org.postgresql.Driver",
                user = "my_username",
                password = "my_password"
            ).also {
                transaction { SchemaUtils.create(DefaultTaskLockTable) }
            }
        }
        mongoDb("my mongodb manager") {
            databaseName = "test"
            client = MongoClient.create("mongodb://host:port")
        }

        task { // if no taskManagerName is provided, the task would be assigned to the default manager
            name = "My task"
            task = { taskExecutionTime ->
                log.info("My task is running: $taskExecutionTime")
            }
            kronSchedule = {
                hours {
                    from(0).every(12)
                }
                minutes {
                    from(10).every(30)
                }
            }
            concurrency = 2
        }

        task(taskManagerName = "my jdbc manager") {
            name = "My Jdbc task"
            // rest of task config
        }
    }
}
