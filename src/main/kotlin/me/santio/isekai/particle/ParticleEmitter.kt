package me.santio.isekai.particle

import net.minestom.server.coordinate.Point
import net.minestom.server.coordinate.Vec
import net.minestom.server.instance.Instance
import net.minestom.server.network.packet.server.play.ParticlePacket
import net.minestom.server.particle.Particle

/**
 * Handles emitting particles at a specific location.
 * @param instance The instance to emit the particles in.
 * @param particle The particle to emit.
 */
@Suppress("MemberVisibilityCanBePrivate")
open class ParticleEmitter(
    private val instance: Instance,
    var particle: Particle,
    var speed: Float = 0f,
    var offset: Vec = Vec.ZERO,
    var blockData: ParticleData? = null
) {

    private fun packet(location: Point, count: Int, data: ParticleData?): ParticlePacket {
        val effectiveData = data ?: blockData
        return ParticlePacket(
            particle.id(),
            false,
            location.x(), location.y(), location.z(),
            offset.x().toFloat(), offset.y().toFloat(), offset.z().toFloat(),
            speed, count, effectiveData?.build() ?: byteArrayOf()
        )
    }

    fun emit(location: Point, data: ParticleData? = null, count: Int = 1) {
        instance.players.forEach {
            it.sendPacket(packet(location, count, data))
        }
    }

}