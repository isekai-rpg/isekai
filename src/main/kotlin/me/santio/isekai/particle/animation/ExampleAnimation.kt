package me.santio.isekai.particle.animation

import me.santio.isekai.helper.minestom.pos
import me.santio.isekai.particle.DustColor
import me.santio.isekai.particle.ParticleData
import me.santio.isekai.particle.ParticleEmitter
import net.minestom.server.coordinate.Pos
import kotlin.math.cos
import kotlin.math.sin

class ExampleAnimation(
    override val emitter: ParticleEmitter,
    private val center: Pos
) : ParticleAnimation(
    emitter,
    50,
) {

    override fun play(emitter: ParticleEmitter, tick: Long) {
        val x = cos(tick / 2.0) * 2
        val z = sin(tick / 2.0) * 2
        val y = sin(tick / 40.0) * 2

        emitter.emit(
            center.add(pos(x, y, z)),
            ParticleData.Dust(
                DustColor(255, 0, 0),
                2f
            )
        )
    }

}