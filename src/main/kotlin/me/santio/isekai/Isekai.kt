package me.santio.isekai

import me.santio.isekai.helper.registerListener
import me.santio.isekai.listeners.PlayerListener
import net.minestom.server.MinecraftServer

fun main() {
    val started = System.currentTimeMillis()
    val server = MinecraftServer.init()

    val eventHandler = MinecraftServer.getGlobalEventHandler()
    eventHandler.registerListener(server, PlayerListener(server))

    server.start("0.0.0.0", 25565)
    println("Server started in ${System.currentTimeMillis() - started}ms")
}