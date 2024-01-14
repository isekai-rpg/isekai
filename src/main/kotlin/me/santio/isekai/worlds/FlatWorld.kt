package me.santio.isekai.worlds

import me.santio.isekai.helper.fillHeight
import me.santio.isekai.helper.id
import me.santio.isekai.helper.pos
import me.santio.isekai.helper.register
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import net.minestom.server.instance.Instance
import net.minestom.server.instance.InstanceManager
import net.minestom.server.instance.block.Block
import net.minestom.server.world.DimensionType

object FlatWorld: World {
    private val dimension = DimensionType.builder(id("flat_world"))
        .ambientLight(2f)
        .build()
        .register()

    override fun get(manager: InstanceManager): Instance {
        val instance = manager.createInstanceContainer(dimension)

        instance.setGenerator {
            it.modifier().fillHeight(0, Block.BEDROCK)
            it.modifier().fillHeight(1, 46, Block.STONE)
            it.modifier().fillHeight(46, 49, Block.DIRT)
            it.modifier().fillHeight(49, Block.GRASS_BLOCK)
        }

        return instance
    }

    override fun spawn(player: Player): Pos {
        return pos(0, 50, 0)
    }
}