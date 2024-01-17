package me.santio.isekai.items.handlers

import kotlinx.serialization.encodeToString
import me.santio.isekai.helper.asCustomItem
import me.santio.isekai.io.JSON
import me.santio.isekai.items.Item
import me.santio.isekai.items.ItemRegistry
import me.santio.isekai.items.types.Sword
import net.minestom.server.entity.LivingEntity
import net.minestom.server.entity.Player
import net.minestom.server.entity.damage.DamageType
import net.minestom.server.event.entity.EntityAttackEvent
import net.minestom.server.event.player.PlayerHandAnimationEvent

class SwordHandler(
    private val registry: ItemRegistry
) {

    fun EntityAttackEvent.on() {
        val victim = target as? LivingEntity ?: return
        val player = entity as? Player ?: return

        val weapon = player.itemInMainHand
            .asCustomItem<Sword>(registry) ?: return

        victim.damage(DamageType.fromPlayer(player), weapon.damage.toFloat())
    }

    fun PlayerHandAnimationEvent.on() {
        val weapon = player.itemInMainHand
            .asCustomItem<Item>(registry) ?: return

        player.sendMessage(JSON.encodeToString(weapon))
    }

}