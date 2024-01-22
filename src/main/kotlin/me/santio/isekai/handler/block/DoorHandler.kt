package me.santio.isekai.handler.block

import me.santio.isekai.helper.BlockRegistry
import me.santio.isekai.helper.BlockRegistry.isHalf
import me.santio.isekai.helper.BlockRegistry.isOpen
import me.santio.isekai.helper.BlockRegistry.setOpen
import me.santio.isekai.helper.BlockRegistry.withFacing
import me.santio.isekai.helper.minestom.direction
import net.kyori.adventure.key.Key
import net.minestom.server.instance.block.Block
import net.minestom.server.instance.block.BlockFace
import net.minestom.server.instance.block.BlockHandler
import net.minestom.server.instance.block.BlockHandler.PlayerPlacement
import net.minestom.server.utils.NamespaceID

object DoorHandler: BlockHandler {
    override fun getNamespaceId(): NamespaceID {
        return NamespaceID.from(Key.key("minecraft:bed"))
    }

    override fun onPlace(placement: BlockHandler.Placement) {
        if (placement.block.isHalf(BlockRegistry.Half.TOP) || placement !is PlayerPlacement) return

        val top = placement.blockPosition.relative(BlockFace.TOP)
        val direction = placement.direction

        placement.instance.setBlock(placement.blockPosition, placement.block.withFacing(direction))
        placement.instance.setBlock(top, placement.block.withFacing(direction).withProperty("half", "upper"))
    }

    override fun onDestroy(destroy: BlockHandler.Destroy) {
        val other = destroy.blockPosition.relative(
            if (destroy.block.isHalf(BlockRegistry.Half.BOTTOM)) BlockFace.TOP else BlockFace.BOTTOM
        )

        destroy.instance.setBlock(other, Block.AIR)
    }

    override fun onInteract(interaction: BlockHandler.Interaction): Boolean {
        val other = interaction.blockPosition.relative(
            if (interaction.block.isHalf(BlockRegistry.Half.BOTTOM)) BlockFace.TOP else BlockFace.BOTTOM
        )

        val isOpen = interaction.block.isOpen()

        interaction.instance.setBlock(interaction.blockPosition, interaction.block.setOpen(!isOpen))
        interaction.instance.setBlock(other, interaction.block.setOpen(!isOpen))

        interaction.player.sendMessage("Pos 1: ${interaction.blockPosition}")
        interaction.player.sendMessage("Pos 2: $other")

        return false
    }
}