package me.santio.isekai.items

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import net.minestom.server.item.Material

@Serializable
data class Item(
    val name: String,
    val rarity: Rarity,
    val lore: String,

    @Contextual
    val material: Material,
) {
    val fileName: String
        get() = name.lowercase().replace(" ", "_")
}