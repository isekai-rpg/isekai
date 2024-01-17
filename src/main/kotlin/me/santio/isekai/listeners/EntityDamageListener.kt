package me.santio.isekai.listeners

import me.santio.isekai.entity.IsekaiCreature
import me.santio.isekai.entity.damage.EntityDamageHandler
import net.minestom.server.attribute.Attribute
import net.minestom.server.attribute.AttributeModifier
import net.minestom.server.entity.Player
import net.minestom.server.entity.damage.DamageType
import net.minestom.server.event.entity.EntityAttackEvent
import net.minestom.server.event.entity.EntityDamageEvent
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

object EntityDamageListener {
    private val damageHandler = EntityDamageHandler()

    fun EntityAttackEvent.on() {
        // player attack isekai (REAL) - tech
        val attacker = entity as? Player
            ?: return
        val victim = target as? IsekaiCreature
            ?: return

        val x = sin(attacker.position.yaw * (PI / 180))
        val z = -cos(attacker.position.yaw * (PI / 180))

        victim.takeKnockback(0.4F, x, z)
        victim.damage(DamageType.fromEntity(attacker), 10.0F)

        println(victim.stats)
    }
}