package me.santio.isekai.helper

import com.github.shynixn.mccoroutine.minestom.addSuspendingListener
import net.minestom.server.MinecraftServer
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import java.lang.reflect.Method
import kotlin.reflect.full.callSuspend
import kotlin.reflect.jvm.kotlinFunction

fun <E : Event> EventNode<in E>.registerListener(server: MinecraftServer, listener: Any) {
    val methods = getEventMethods<E>(listener)

    methods.forEach { (clazz, method) ->
        method.isAccessible = true
        val isSuspend = method.kotlinFunction?.isSuspend ?: false

        if (isSuspend) {
            this.addSuspendingListener(server, clazz) { event ->
                method.kotlinFunction!!.callSuspend(listener, event)
            }
        } else {
            this.addListener(clazz) { event ->
                method.invoke(listener, event)
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
private fun <E : Event> getEventMethods(listener: Any): List<Pair<Class<E>, Method>> {
    val methods = listener::class.java.methods

    val paired = methods
        .filter { it.parameters.isNotEmpty() }
        .map { it.parameters.first() to it }

    return paired.filter { Event::class.java.isAssignableFrom(it.first.type) }
        .map { it.first.type as Class<E> to it.second }
}