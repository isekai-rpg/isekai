package me.santio.isekai.listeners

import me.santio.isekai.helper.BlockRegistry
import me.santio.isekai.helper.BlockRegistry.isKind
import net.minestom.server.event.player.PlayerBlockPlaceEvent
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.block.BlockHandler
import org.antlr.v4.runtime.misc.Predicate

object GlobalBlockHandler {

    private val staticHandlers = mutableMapOf<Block, BlockHandler>()
    private val predicateHandlers = mutableMapOf<Predicate<Block>, BlockHandler>()

    fun register(block: Block, handler: BlockHandler) {
        staticHandlers[block] = handler
    }

    fun register(handler: BlockHandler, predicate: Predicate<Block>) {
        predicateHandlers[predicate] = handler
    }

    fun PlayerBlockPlaceEvent.on() {
        val handler = staticHandlers[block]
            ?: predicateHandlers.entries.firstOrNull { it.key.test(block) }?.value
            ?: return


        if (block.isKind(BlockRegistry.BlockKind.DOOR)) {
            isCancelled = true
            instance.setBlock(blockPosition, block.withHandler(handler))
        }
    }
}