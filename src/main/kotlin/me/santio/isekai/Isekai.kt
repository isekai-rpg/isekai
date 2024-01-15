package me.santio.isekai

import me.santio.isekai.commands.GamemodeCommand
import me.santio.isekai.helper.registerListener
import me.santio.isekai.items.Item
import me.santio.isekai.items.ItemRegistry
import me.santio.isekai.items.Rarity
import me.santio.isekai.listeners.DoorListener
import me.santio.isekai.listeners.GlobalBlockHandler
import me.santio.isekai.listeners.PlayerListener
import me.santio.isekai.worlds.IntroWorld
import net.minestom.server.MinecraftServer
import net.minestom.server.item.Material

fun main() {
    val started = System.currentTimeMillis()
    val server = MinecraftServer.init()
    val items = ItemRegistry()

    MinecraftServer.getCommandManager().register(GamemodeCommand)

    val eventHandler = MinecraftServer.getGlobalEventHandler()
    eventHandler.registerListener(server, PlayerListener(server))
    eventHandler.registerListener(server, GlobalBlockHandler)
    eventHandler.registerListener(server, DoorListener)

    items.load()
    items.register(Item("test", Rarity.COMMON, "test", Material.STONE))
    items.save()

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