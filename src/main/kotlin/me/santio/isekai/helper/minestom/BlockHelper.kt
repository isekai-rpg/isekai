package me.santio.isekai.helper.minestom

import net.minestom.server.coordinate.Pos
import net.minestom.server.coordinate.Vec
import net.minestom.server.instance.block.BlockFace
import net.minestom.server.instance.block.BlockHandler

fun pos(x: Number, y: Number, z: Number): Pos {
    return Pos(
        if (x is Int) x.toDouble() + 0.5 else x.toDouble(),
        y.toDouble(),
        if (z is Int) z.toDouble() + 0.5 else z.toDouble()
    )
}

fun Vec.toBlockFace(): BlockFace {
    val normalized = this.normalize()

    return if (kotlin.math.abs(normalized.x()) > kotlin.math.abs(normalized.z())) {
        if (normalized.x() > 0) BlockFace.EAST else BlockFace.WEST
    } else {
        if (normalized.z() > 0) BlockFace.SOUTH else BlockFace.NORTH
    }
}

val BlockHandler.Placement.direction: BlockFace
    get() {
        return if (this is BlockHandler.PlayerPlacement) this.player.position.direction().toBlockFace() else BlockFace.NORTH
    }