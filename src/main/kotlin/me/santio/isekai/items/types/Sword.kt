package me.santio.isekai.items.types

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.santio.isekai.items.Item
import me.santio.isekai.items.Rarity
import net.minestom.server.item.Material

@Serializable
@SerialName("sword")
class Sword(
    override val name: String,
    override val rarity: Rarity,
    override val lore: String,
    @Contextual
    override val material: Material,

    val damage: Int,
): Item