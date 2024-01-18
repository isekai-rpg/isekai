package me.santio.isekai.particle.animation

import me.santio.isekai.particle.DustColor
import me.santio.isekai.particle.ParticleData
import me.santio.isekai.particle.ParticleEmitter
import me.santio.isekai.particle.ShapeHelper.getSphere
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.coordinate.Pos

class SmallExplosionAnimation(
    override val emitter: ParticleEmitter,
    private val center: Pos,
    private val color: DustColor = DustColor(NamedTextColor.DARK_GRAY)
) : ParticleAnimation(
    emitter,
    50,
) {

    override fun play(emitter: ParticleEmitter, tick: Long) {
        val radius = 0.1 * tick
        val sphere = getSphere(radius, 15).map { it.add(center) }

        sphere.forEach {
            emitter.emit(it, ParticleData.Dust(
                color,
                0.1f
            ))
        }

        if (tick > 5) stop()
    }

}