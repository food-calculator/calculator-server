package de.fridolin1

import de.fridolin1.modules.enableCors
import de.fridolin1.modules.configureMonitoring
import de.fridolin1.modules.configureRouting
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    enableCors()
    configureMonitoring()
    configureRouting()
}
