package me.santio.isekai.commands

//import me.santio.isekai.entity.types.EliteZombieCreature
import me.santio.isekai.entity.types.EliteZombieCreature
import net.minestom.server.MinecraftServer
import net.minestom.server.command.builder.Command
import net.minestom.server.entity.Player
import net.minestom.server.timer.TaskSchedule

object SpawnEntity : Command("spawnentity") {
    init {
        setDefaultExecutor { sender, _ ->
            val player = sender as? Player
                ?: return@setDefaultExecutor
            val instance = player.instance

            val creature = EliteZombieCreature()
//            creature.triggerStatus(32)
            MinecraftServer.getSchedulerManager()
                .scheduleTask({
//                      creature.triggerStatus(4)
//                    player.triggerStatus(NamedEntityStatus.IN_HONEY)
//                    player.triggerHurt()
                }, TaskSchedule.tick(20), TaskSchedule.tick(20))
            creature.setInstance(instance, player.position)
        }
    }
}