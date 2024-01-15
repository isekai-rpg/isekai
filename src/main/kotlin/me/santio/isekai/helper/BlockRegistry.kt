package me.santio.isekai.helper

import net.minestom.server.instance.block.Block
import net.minestom.server.instance.block.BlockFace

object BlockRegistry {

    fun Block.isKind(kind: BlockKind): Boolean {
        return this.name().contains(kind.search, true)
    }

    fun Block.isHalf(half: Half): Boolean {
        return this.getProperty("half") == half.value
    }

    fun Block.withFacing(facing: BlockFace): Block {
        return this.withProperty("facing", facing.toString().lowercase())
    }

    fun Block.isOpen(): Boolean {
        return this.getProperty("open") == "true"
    }

    fun Block.setOpen(opened: Boolean = true): Block {
        return this.withProperty("open", opened.toString())
    }

    enum class Half(internal val value: String) {
        TOP("upper"),
        BOTTOM("lower")
    }

    enum class BlockKind(internal val search: String) {
        DOOR("_DOOR"),
        BUTTON("_BUTTON")
    }

}