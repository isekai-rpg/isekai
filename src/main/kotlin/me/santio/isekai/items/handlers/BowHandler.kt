package me.santio.isekai.items.handlers

import headPosition
import kotlinx.serialization.encodeToString
import me.santio.isekai.entity.Arrow
import me.santio.isekai.helper.asCustomItem
import me.santio.isekai.io.JSON
import me.santio.isekai.items.ItemRegistry
import me.santio.isekai.items.types.Bow
import net.minestom.server.entity.Player
import net.minestom.server.event.item.ItemUpdateStateEvent
import net.minestom.server.event.player.PlayerHandAnimationEvent

class BowHandler(
    private val registry: ItemRegistry
) {

    fun ItemUpdateStateEvent.on() {
        val player = entity as? Player ?: return

        val weapon = player.itemInMainHand
            .asCustomItem<Bow>(registry) ?: return

        val arrow = Arrow(player)
        arrow.setInstance(player.instance!!, player.headPosition)
        arrow.shoot(player, 1.0, 0.0)
    }

    fun PlayerHandAnimationEvent.on() {
        val weapon = player.itemInMainHand
            .asCustomItem<Bow>(registry) ?: return

        player.sendMessage(JSON.encodeToString(weapon))
    }

}