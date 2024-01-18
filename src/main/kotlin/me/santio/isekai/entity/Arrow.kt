package me.santio.isekai.entity

import headPosition
import net.minestom.server.coordinate.Point
import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityProjectile
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.Player

class Arrow(
    shooter: Entity
): EntityProjectile(shooter, EntityType.ARROW) {

    init {
        isAutoViewable = false
    }

    override fun shoot(to: Point?, power: Double, spread: Double) {
        super.shoot(to, power, spread)
        isAutoViewable = true
    }

    fun shoot(entity: Entity, power: Double, spread: Double) {
        val position = entity.headPosition.add(entity.position.direction().mul(10.0))
        if (entity is Player) entity.sendMessage("Shooting at $position")
        shoot(position, power, spread)
    }

    override fun tick(time: Long) {
        super.tick(time)
        position = position.withDirection(
            position.direction().withY((velocity.y / velocity.length()) * -1)
        )
    }

    fun disappear() {

    }

}