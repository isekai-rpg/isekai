package me.santio.isekai.helper.minestom

import net.minestom.server.coordinate.Pos
import net.minestom.server.coordinate.Vec
import net.minestom.server.instance.block.BlockFace
import net.minestom.server.instance.block.BlockHandler
import kotlin.math.abs

fun pos(x: Number, y: Number, z: Number, yaw: Number? = 0, pitch: Number? = 0): Pos {
    return Pos(
        vec(x, y, z),
        yaw?.toFloat() ?: 0.0f,
        pitch?.toFloat() ?: 0.0f
    )
}

fun vec(x: Number, y: Number, z: Number): Vec {
    return Vec(
        if (x is Int) x.toDouble() else x.toDouble(),
        y.toDouble(),
        if (z is Int) z.toDouble() else z.toDouble()
    )
}

fun Vec.toBlockFace(): BlockFace {
    val normalized = this.normalize()

    return if (abs(normalized.x()) > abs(normalized.z())) {
        if (normalized.x() > 0) BlockFace.EAST else BlockFace.WEST
    } else {
        if (normalized.z() > 0) BlockFace.SOUTH else BlockFace.NORTH
    }
}

val BlockHandler.Placement.direction: BlockFace
    get() {
        return if (this is BlockHandler.PlayerPlacement) this.player.position.direction().toBlockFace() else BlockFace.NORTH
    }