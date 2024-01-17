package me.santio.isekai.commands

import me.santio.isekai.entity.types.EliteZombieCreature
import net.minestom.server.command.builder.Command
import net.minestom.server.entity.Player

object SpawnEntity : Command("s") {
    init {
        setDefaultExecutor { sender, _ ->
            val player = sender as? Player
                ?: return@setDefaultExecutor
            val instance = player.instance

            val creature = EliteZombieCreature()
                .also { println(it) }
            creature.setInstance(instance, player.position)
        }
    }
}