package me.santio.isekai.particle.animation

import me.santio.isekai.particle.ParticleEmitter
import net.minestom.server.MinecraftServer
import net.minestom.server.timer.ExecutionType
import net.minestom.server.timer.Task
import net.minestom.server.timer.TaskSchedule

abstract class ParticleAnimation(
    open val emitter: ParticleEmitter,
    private val ups: Int = 50,
) {

    private var task: Task? = null

    private var tick: Long = 0

    private fun tick() {
        tick++
        play(emitter, tick)
    }

    abstract fun play(emitter: ParticleEmitter, tick: Long)

    fun start() {
        task?.cancel()
        task = MinecraftServer.getSchedulerManager().scheduleTask({
            tick()
        }, TaskSchedule.immediate(), TaskSchedule.millis(1000L / ups), ExecutionType.ASYNC)
    }

    fun stop() {
        task?.cancel()
    }

}