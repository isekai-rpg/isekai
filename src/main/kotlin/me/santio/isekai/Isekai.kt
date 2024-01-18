package me.santio.isekai

import io.github.cdimascio.dotenv.dotenv
import me.santio.isekai.commands.GamemodeCommand
import me.santio.isekai.commands.ItemCommand
import me.santio.isekai.commands.ParticleCommand
import me.santio.isekai.helper.registerListener
import me.santio.isekai.items.ItemRegistry
import me.santio.isekai.items.handlers.BowHandler
import me.santio.isekai.items.handlers.SwordHandler
import me.santio.isekai.listeners.DoorListener
import me.santio.isekai.listeners.GlobalBlockHandler
import me.santio.isekai.listeners.PlayerListener
import me.santio.isekai.particle.ParticleRegistry
import me.santio.isekai.worlds.IntroWorld
import net.minestom.server.MinecraftServer
import net.minestom.server.extras.velocity.VelocityProxy
import java.io.InputStream
import kotlin.system.measureTimeMillis


// todo: configure - tech
private val ENVIRONMENT = Environment.DEV

val dotEnv = dotenv {
    filename = if(ENVIRONMENT.isProd) ".env" else ".env.local"
}


fun main() {
    val startupTime = measureTimeMillis {
        bootstrap()
    }
    println("Server started in ${startupTime}ms.")

    registerShutdownLogic()
}

/**
 * Initializes the server.
 */
private fun bootstrap() {
    val server = MinecraftServer.init()
    val items = ItemRegistry().load()
    val emitters = ParticleRegistry()

    initializeVelocity()

    val commandManager = MinecraftServer.getCommandManager()
    commandManager.register(GamemodeCommand)
    commandManager.register(ItemCommand(items))
    commandManager.register(ParticleCommand(emitters))

    val eventHandler = MinecraftServer.getGlobalEventHandler()
    eventHandler.registerListener(server, PlayerListener(server))
    eventHandler.registerListener(server, GlobalBlockHandler)
    eventHandler.registerListener(server, DoorListener)
    eventHandler.registerListener(server, SwordHandler(items))
    eventHandler.registerListener(server, BowHandler(items))

    server.start("0.0.0.0", 25565)
}

private fun initializeVelocity() {
    val secret = dotEnv.get("VELOCITY_SECRET")
    if(secret == null) {
        println("velocity secret is not set, skipping velocity initialization.")
        return
    }

    println("Enabling Velocity support.")
    VelocityProxy.enable(secret)
}

/**
 * Builds a shutdown task from [net.minestom.server.timer.SchedulerManager].
 */
private fun registerShutdownLogic() {
    MinecraftServer.getSchedulerManager().buildShutdownTask {
        IntroWorld.save()
    }
}

fun resource(path: String): InputStream? {
    return object {}.javaClass.classLoader.getResourceAsStream(path)
}