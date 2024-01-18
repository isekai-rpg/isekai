package me.santio.isekai.particle

import net.minestom.server.coordinate.Point
import net.minestom.server.instance.Instance
import net.minestom.server.network.packet.server.play.ParticlePacket
import net.minestom.server.particle.Particle
import net.minestom.server.utils.PacketUtils


/**
 * Stores and handles particle emitters.
 */
class ParticleRegistry {

    fun play(particle: Particle, location: Point) {
        PacketUtils.broadcastPacket(ParticlePacket(
            particle.id(),
            false,
            location.x(), location.y(), location.z(),
            0f, 0f, 0f,
            0f, 1, byteArrayOf()
        ))
    }

    /**
     * Tick all particle emitters.
     * @param instance The instance to tick the emitters in.
     */
    fun tick(instance: Instance) {

    }

}