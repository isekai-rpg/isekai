package me.santio.isekai.items

import net.kyori.adventure.text.format.TextColor

enum class Rarity(val title: String, val color: TextColor) {
    COMMON("Common", TextColor.color(0xAAAAAA)),
    UNCOMMON("Uncommon", TextColor.color(0x55FF55)),
    RARE("Rare", TextColor.color(0x5555FF)),
    EPIC("Epic", TextColor.color(0xAA00AA)),
    LEGENDARY("Legendary", TextColor.color(0xFFAA00)),
    MYTHIC("Mythic", TextColor.color(0xFF5555)),
    ADMIN("Administrator", TextColor.color(0xFF2222)),
    UNIQUE("Unique", TextColor.color(0x55FFFF)),
    QUEST("Quest", TextColor.color(0xAAAAAA)),
    EVENT("Event", TextColor.color(0x55FFFF)),
    ;
}