package me.santio.isekai

import me.santio.isekai.commands.GamemodeCommand
import me.santio.isekai.commands.SpawnEntity
import me.santio.isekai.handler.block.DoorHandler
import me.santio.isekai.helper.BlockRegistry
import me.santio.isekai.helper.BlockRegistry.isKind
import me.santio.isekai.helper.registerListener
import me.santio.isekai.listeners.EntityListener
import me.santio.isekai.listeners.GlobalBlockHandler
import me.santio.isekai.listeners.PlayerListener
import me.santio.isekai.worlds.IntroWorld
import net.minestom.server.MinecraftServer

fun main() {
    val started = System.currentTimeMillis()
    val server = MinecraftServer.init()

    val commandManager = MinecraftServer.getCommandManager()
    commandManager.register(GamemodeCommand)
    commandManager.register(SpawnEntity)

    val eventHandler = MinecraftServer.getGlobalEventHandler()
    eventHandler.registerListener(server, PlayerListener(server))
    eventHandler.registerListener(server, GlobalBlockHandler)
    eventHandler.registerListener(server, EntityListener)

    GlobalBlockHandler.register(DoorHandler) { it.isKind(BlockRegistry.BlockKind.DOOR) }
    eventHandler.registerListener(server, GlobalBlockHandler)

    server.start("0.0.0.0", 25565)
    println("Server started in ${System.currentTimeMillis() - started}ms")

    registerShutdownLogic()
}

/**
 * Builds a shutdown task from [net.minestom.server.timer.SchedulerManager].
 */
private fun registerShutdownLogic() {
    MinecraftServer.getSchedulerManager().buildShutdownTask {
        IntroWorld.save()
    }
}