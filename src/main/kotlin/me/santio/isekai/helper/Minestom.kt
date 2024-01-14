package me.santio.isekai.helper

import net.minestom.server.MinecraftServer
import net.minestom.server.coordinate.Pos
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.generator.UnitModifier
import net.minestom.server.utils.NamespaceID
import net.minestom.server.world.DimensionType

fun id(id: String) = NamespaceID.from("isekai", id)

fun pos(x: Number, y: Number, z: Number): Pos {
    return Pos(
        if (x is Int) x.toDouble() + 0.5 else x.toDouble(),
        y.toDouble(),
        if (z is Int) z.toDouble() + 0.5 else z.toDouble()
    )
}

fun DimensionType.register(): DimensionType {
    val manager = MinecraftServer.getDimensionTypeManager()
    if (!manager.isRegistered(this)) manager.addDimension(this)

    return this
}

fun UnitModifier.fillHeight(height: Int, block: Block) = this.fillHeight(height, height + 1, block)