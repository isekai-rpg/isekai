package me.santio.isekai.particle

import net.kyori.adventure.text.format.TextColor
import java.awt.Color

@Suppress("unused")
data class DustColor(
    val red: Int,
    val green: Int,
    val blue: Int
) {
    constructor(color: Color): this(color.red, color.green, color.blue)
    constructor(hex: String): this(Color.decode(hex))
    constructor(color: TextColor): this(color.red(), color.green(), color.blue())
}