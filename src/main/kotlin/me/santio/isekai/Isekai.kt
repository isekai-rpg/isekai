package me.santio.isekai

import me.santio.isekai.commands.GamemodeCommand
import me.santio.isekai.commands.ItemCommand
import me.santio.isekai.helper.registerListener
import me.santio.isekai.items.ItemRegistry
import me.santio.isekai.listeners.DoorListener
import me.santio.isekai.listeners.GlobalBlockHandler
import me.santio.isekai.listeners.PlayerListener
import me.santio.isekai.worlds.IntroWorld
import net.minestom.server.MinecraftServer

fun main() {
    val started = System.currentTimeMillis()
    val server = MinecraftServer.init()
    val items = ItemRegistry().load()

    MinecraftServer.getCommandManager().register(GamemodeCommand)
    MinecraftServer.getCommandManager().register(ItemCommand(items))

    val eventHandler = MinecraftServer.getGlobalEventHandler()
    eventHandler.registerListener(server, PlayerListener(server))
    eventHandler.registerListener(server, GlobalBlockHandler)
    eventHandler.registerListener(server, DoorListener)

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