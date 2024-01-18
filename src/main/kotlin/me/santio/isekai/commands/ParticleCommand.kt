package me.santio.isekai.commands

import headPosition
import me.santio.isekai.helper.minestom.addSyntax
import me.santio.isekai.particle.ParticleEmitter
import me.santio.isekai.particle.animation.SmallExplosionAnimation
import net.minestom.server.command.builder.Command
import net.minestom.server.entity.Player
import net.minestom.server.particle.Particle

object ParticleCommand: Command("particle") {

    init {
        setDefaultExecutor { sender, _ ->
            sender.sendMessage("Usage: /particle")
        }

        addSyntax { sender, context ->
            if (sender !is Player) return@addSyntax sender.sendMessage("You must be a player to use this command!")

            val emitter = ParticleEmitter(sender.instance, Particle.DUST)
            val animation = SmallExplosionAnimation(emitter, sender.headPosition)
            animation.start()
        }
    }

}