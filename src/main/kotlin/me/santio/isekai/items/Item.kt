package me.santio.isekai.items

import me.santio.isekai.helper.mm
import me.santio.isekai.helper.wrap
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import net.minestom.server.item.ItemHideFlag
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import net.minestom.server.tag.Tag

interface Item {
    val name: String
    val rarity: Rarity
    val lore: String
    val material: Material

    val fileName: String
        get() = name.lowercase().replace(" ", "_")

    fun build(width: Int = 30): ItemStack {
        return ItemStack.builder(material)
            .displayName(
                mm.deserialize(
                    "<color>$name",
                    Placeholder.styling("color", rarity.color)
                ).decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE))
            .lore(
                mm.deserialize("<gray><st>${" ".repeat(width + 5)}<gray>"),
                *lore.wrap(width).map { mm.deserialize("<gray>$it") }.toTypedArray(),
                mm.deserialize("<gray><st>${" ".repeat(width + 5)}<gray>"),
            )
            .meta { it.hideFlag(*ItemHideFlag.entries.toTypedArray()) }
            .apply { setTag(Tag.String("isekai_item"), name) }
            .build()
    }
}