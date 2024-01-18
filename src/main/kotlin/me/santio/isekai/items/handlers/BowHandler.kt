package me.santio.isekai.items.handlers

import kotlinx.serialization.encodeToString
import me.santio.isekai.entity.Arrow
import me.santio.isekai.helper.asCustomItem
import me.santio.isekai.io.JSON
import me.santio.isekai.items.ItemRegistry
import me.santio.isekai.items.types.Bow
import net.minestom.server.event.player.PlayerHandAnimationEvent
import net.minestom.server.event.player.PlayerUseItemEvent

class BowHandler(
    private val registry: ItemRegistry
) {

    fun PlayerUseItemEvent.on() {
        val weapon = player.itemInMainHand
            .asCustomItem<Bow>(registry) ?: return

        val arrow = Arrow()
        arrow.setInstance(player.instance!!, player.position)
    }

    fun PlayerHandAnimationEvent.on() {
        val weapon = player.itemInMainHand
            .asCustomItem<Bow>(registry) ?: return

        player.sendMessage(JSON.encodeToString(weapon))
    }

}