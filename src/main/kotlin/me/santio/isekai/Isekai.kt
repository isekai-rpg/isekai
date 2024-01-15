package me.santio.isekai

import me.santio.isekai.commands.GamemodeCommand
import me.santio.isekai.helper.registerListener
import me.santio.isekai.listeners.DoorListener
import me.santio.isekai.listeners.GlobalBlockHandler
import me.santio.isekai.listeners.PlayerListener
import me.santio.isekai.worlds.IntroWorld
import net.minestom.server.MinecraftServer

fun main() {
    val started = System.currentTimeMillis()
    val server = MinecraftServer.init()

    MinecraftServer.getCommandManager().register(GamemodeCommand)

    val eventHandler = MinecraftServer.getGlobalEventHandler()
    eventHandler.registerListener(server, PlayerListener(server))
    eventHandler.registerListener(server, GlobalBlockHandler)
    eventHandler.registerListener(server, DoorListener)

    server.start("0.0.0.0", 25565)
    println("Server started in ${System.currentTimeMillis() - started}ms")

    Runtime.getRuntime().addShutdownHook(Thread {
        IntroWorld.save()
    })
}