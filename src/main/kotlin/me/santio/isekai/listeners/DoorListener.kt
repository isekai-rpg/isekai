package me.santio.isekai.listeners

import me.santio.isekai.helper.BlockRegistry
import me.santio.isekai.helper.BlockRegistry.asHalf
import me.santio.isekai.helper.BlockRegistry.isHalf
import me.santio.isekai.helper.BlockRegistry.isKind
import me.santio.isekai.helper.BlockRegistry.isOpen
import me.santio.isekai.helper.BlockRegistry.setOpen
import me.santio.isekai.helper.BlockRegistry.withFacing
import me.santio.isekai.helper.minestom.toBlockFace
import net.minestom.server.event.player.PlayerBlockBreakEvent
import net.minestom.server.event.player.PlayerBlockInteractEvent
import net.minestom.server.event.player.PlayerBlockPlaceEvent
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.block.BlockFace

object DoorListener {

    fun PlayerBlockPlaceEvent.on() {
        if (!block.isKind(BlockRegistry.BlockKind.DOOR)) return

        if (block.isHalf(BlockRegistry.Half.TOP)) return

        val top = blockPosition.relative(BlockFace.TOP)
        val door = block.withFacing(player.position.direction().toBlockFace())

        block = door.asHalf(BlockRegistry.Half.BOTTOM)
        instance.setBlock(
            top,
            door.asHalf(BlockRegistry.Half.TOP)
        )
    }

    fun PlayerBlockBreakEvent.on() {
        if (!block.isKind(BlockRegistry.BlockKind.DOOR)) return

        val other = blockPosition.relative(
            if (block.isHalf(BlockRegistry.Half.BOTTOM)) BlockFace.TOP else BlockFace.BOTTOM
        )

        instance.setBlock(other, Block.AIR)
    }

    fun PlayerBlockInteractEvent.on() {
        if (!block.isKind(BlockRegistry.BlockKind.DOOR)) return

        val open = !block.isOpen()
        val other = blockPosition.relative(
            if (block.isHalf(BlockRegistry.Half.BOTTOM)) BlockFace.TOP else BlockFace.BOTTOM
        )

        isBlockingItemUse = true
        instance.setBlock(blockPosition, block.setOpen(open))
        instance.setBlock(other, instance.getBlock(other).setOpen(open))
    }

}