package me.santio.isekai.entity

import headPosition
import me.santio.isekai.helper.minestom.vec
import me.santio.isekai.particle.DustColor
import me.santio.isekai.particle.ParticleData
import me.santio.isekai.particle.ParticleEmitter
import me.santio.isekai.particle.ParticlePlayer
import me.santio.isekai.particle.animation.SmallExplosionAnimation
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.MinecraftServer
import net.minestom.server.coordinate.Point
import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityProjectile
import net.minestom.server.entity.EntityType
import net.minestom.server.particle.Particle
import java.time.Duration

class Arrow(
    shooter: Entity
): EntityProjectile(shooter, EntityType.ARROW) {

    init {
        isAutoViewable = false
    }

    override fun shoot(to: Point?, power: Double, spread: Double) {
        super.shoot(to, power, spread)
        isAutoViewable = true

        MinecraftServer.getSchedulerManager().buildTask {
            disappear()
        }.delay(Duration.ofSeconds(15)).schedule()
    }

    fun shoot(entity: Entity, power: Double, spread: Double) {
        val position = entity.headPosition.add(entity.position.direction().mul(10.0))
        shoot(position, power, spread)
    }

    override fun tick(time: Long) {
        super.tick(time)
        position = position.withDirection(
            position.direction().withY((velocity.y / velocity.length()) * -1)
        )
    }

    private fun disappear() {
        val player = ParticlePlayer(
            position,
            ParticleEmitter(
                instance,
                Particle.DUST,
                offset = vec(0.05, 0.1, 0.05)
            ),
            count = 10
        )

        player.start(ParticleData.Dust(
            DustColor(NamedTextColor.DARK_GRAY),
            0.5f
        ))

        MinecraftServer.getSchedulerManager().buildTask {
            val emitter = ParticleEmitter(instance, Particle.DUST)
            val animation = SmallExplosionAnimation(emitter, position)
            animation.start()

            player.stop()
            remove()
        }.delay(Duration.ofSeconds(5)).schedule()
    }

}