package me.santio.isekai.commands

import me.santio.isekai.helper.minestom.addSyntax
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.GameMode
import net.minestom.server.entity.Player

object GamemodeCommand: Command("gamemode") {

    init {
        setDefaultExecutor { sender, _ ->
            sender.sendMessage("Usage: /gamemode <mode>")
        }

        val mode = ArgumentType.String("mode").apply {
            setCallback { sender, error ->
                sender.sendMessage("Invalid gamemode " + error.input)
            }
        }

        addSyntax(mode) { sender, context ->
            val gamemode = GameMode.entries.firstOrNull { it.name.startsWith(context.get(mode).uppercase()) }
                ?: return@addSyntax sender.sendMessage("Invalid gamemode " + context.get(mode))

            if (sender !is Player) return@addSyntax sender.sendMessage("You must be a player to use this command!")

            sender.setGameMode(gamemode)
            sender.sendMessage("Your gamemode was set to ${gamemode.name.lowercase()}")
        }
    }

}