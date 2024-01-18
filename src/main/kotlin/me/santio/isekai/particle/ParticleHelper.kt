package me.santio.isekai.particle

import net.minestom.server.coordinate.Pos
import net.minestom.server.instance.Instance
import net.minestom.server.particle.Particle

fun particle(
    instance: Instance,
    position: Pos,
    particle: Particle,
    data: ParticleData? = null,
    amount: Int = 1,
    extra: ParticleEmitter.() -> Unit = {}
) {
    val emitter = ParticleEmitter(instance, particle)
    emitter.extra()
    emitter.emit(position, data, amount)
}