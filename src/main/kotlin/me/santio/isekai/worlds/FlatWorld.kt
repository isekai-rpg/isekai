package me.santio.isekai.worlds

import me.santio.isekai.helper.minestom.fillHeight
import me.santio.isekai.helper.minestom.id
import me.santio.isekai.helper.minestom.pos
import me.santio.isekai.helper.minestom.register
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import net.minestom.server.instance.Instance
import net.minestom.server.instance.block.Block
import net.minestom.server.world.DimensionType

object FlatWorld: World() {
    override val dimension = DimensionType.builder(id("flat_world"))
        .ambientLight(2f)
        .build()
        .register()

    override fun generate(instance: Instance) {
        instance.setGenerator {
            it.modifier().fillHeight(0, Block.BEDROCK)
            it.modifier().fillHeight(1, 46, Block.STONE)
            it.modifier().fillHeight(46, 49, Block.DIRT)
            it.modifier().fillHeight(49, Block.GRASS_BLOCK)
        }
    }

    override fun spawn(player: Player): Pos {
        return pos(0, 50, 0)
    }
}