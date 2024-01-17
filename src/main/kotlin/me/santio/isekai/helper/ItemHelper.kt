package me.santio.isekai.helper

import me.santio.isekai.items.Item
import me.santio.isekai.items.ItemRegistry
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag

inline fun <reified T: Item> ItemStack.asCustomItem(itemRegistry: ItemRegistry): T? {
    val tag = this.getTag(Tag.String("isekai_item"))
        ?: return null

    val item = itemRegistry.items.firstOrNull {
        it.name == tag
    } ?: error("Item not found")

    return item as? T
}