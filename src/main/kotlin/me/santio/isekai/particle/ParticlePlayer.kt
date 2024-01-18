package me.santio.isekai.particle

import net.minestom.server.MinecraftServer
import net.minestom.server.coordinate.Pos
import net.minestom.server.timer.ExecutionType
import net.minestom.server.timer.Task
import net.minestom.server.timer.TaskSchedule

/**
 * Plays particles at a specific location.
 * @param location The location to play the particles at.
 * @param emitter The emitter to emit the particles in.
 * @param ups The amount of updates per second.
 */
@Suppress("MemberVisibilityCanBePrivate")
class ParticlePlayer(
    var location: Pos,
    private val emitter: ParticleEmitter,
    private val ups: Int = 50,
) {

    private var task: Task? = null
    var count: Int = 1

    fun play(data: ParticleData? = null, count: Int = 1): ParticlePlayer {
        emitter.emit(location, data, count)
        return this
    }

    fun start(data: ParticleData? = null): ParticlePlayer {
        task?.cancel()

        task = MinecraftServer.getSchedulerManager().scheduleTask({
            emitter.emit(location, data, count)
        }, TaskSchedule.immediate(), TaskSchedule.millis(1000L / ups), ExecutionType.ASYNC)

        return this
    }

    fun stop(): ParticlePlayer {
        task?.cancel()
        return this
    }

}