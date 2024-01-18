package me.santio.isekai.helper.minestom

import net.minestom.server.MinecraftServer
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandExecutor
import net.minestom.server.command.builder.CommandSyntax
import net.minestom.server.command.builder.arguments.Argument
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.generator.UnitModifier
import net.minestom.server.utils.NamespaceID
import net.minestom.server.world.DimensionType

fun id(id: String) = NamespaceID.from("isekai", id)

fun DimensionType.register(): DimensionType {
    val manager = MinecraftServer.getDimensionTypeManager()
    if (!manager.isRegistered(this)) manager.addDimension(this)

    return this
}

fun UnitModifier.fillHeight(height: Int, block: Block) = this.fillHeight(height, height + 1, block)

fun Command.addSyntax(vararg args: Argument<*>, executor: CommandExecutor): Collection<CommandSyntax> {
    return this.addSyntax(executor, *args)
}