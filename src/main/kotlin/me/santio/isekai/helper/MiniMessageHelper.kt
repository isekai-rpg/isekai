package me.santio.isekai.helper

import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import net.minestom.server.command.CommandSender

val mm = MiniMessage.builder()
    .preProcessor { "<i:false>$it" }
    .build()

fun CommandSender.sendFormatted(message: String, vararg resolver: TagResolver) {
    this.sendMessage(mm.deserialize(message, *resolver))
}