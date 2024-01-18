package me.santio.isekai.commands

import me.santio.isekai.helper.addSyntax
import me.santio.isekai.helper.sendFormatted
import me.santio.isekai.items.ItemRegistry
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.suggestion.SuggestionEntry
import net.minestom.server.entity.Player


class ParticleCOmmand(
    private val itemRegistry: ItemRegistry
): Command("item") {

    init {
        setDefaultExecutor { sender, _ ->
            sender.sendMessage("Usage: /item <name>")
        }

        val mode = ArgumentType.StringArray("name").apply {
            setCallback { sender, error ->
                sender.sendMessage("Invalid item " + error.input)
            }

            setSuggestionCallback { _, context, suggestion ->
                val name = context.input
                itemRegistry.items
                    .filter { it.fileName.startsWith(name, ignoreCase = true) }
                    .forEach { suggestion.addEntry(SuggestionEntry(it.name, Component.text(it.name))) }
            }
        }

        addSyntax(mode) { sender, context ->
            if (sender !is Player) return@addSyntax sender.sendMessage("You must be a player to use this command!")

            val name = context.get(mode).joinToString(" ")
            val item = itemRegistry.items.find { it.name.equals(name, true) }
                ?: return@addSyntax sender.sendMessage("Invalid item '$name', available: ${itemRegistry.items.joinToString(", ") { it.name }}")

            sender.inventory.addItemStack(item.build())
            sender.sendFormatted(
                "You were given a <color>${item.name}",
                Placeholder.styling("color", item.rarity.color)
            )
        }
    }

}