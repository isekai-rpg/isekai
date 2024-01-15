package me.santio.isekai.listeners

import net.minestom.server.event.entity.EntityAttackEvent
import net.minestom.server.event.entity.EntityDamageEvent
import net.minestom.server.event.player.PlayerMoveEvent

object EntityListener {
    fun EntityAttackEvent.on() {
        println("Entity Damage")
    }
}