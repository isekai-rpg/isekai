package me.santio.isekai.listeners

import com.github.shynixn.mccoroutine.minestom.asyncDispatcher
import com.github.shynixn.mccoroutine.minestom.launch
import me.santio.isekai.io.mojang.UnifiedMojangAPI
import me.santio.isekai.worlds.FlatWorld
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.GameMode
import net.minestom.server.entity.PlayerSkin
import net.minestom.server.event.player.PlayerLoginEvent
import net.minestom.server.event.player.PlayerSkinInitEvent

class PlayerListener(
    private val server: MinecraftServer
) {
    private val mojangAPI = UnifiedMojangAPI()

    suspend fun PlayerSkinInitEvent.on() {
        server.launch {
            val skin = mojangAPI.get(player.username)
                .fold(
                    onSuccess = { it.textures.skin },
                    onFailure = { null }
                )
            if(skin == null) {
                player.sendMessage(Component.text("Unable to load skin.", NamedTextColor.RED))
                return@launch
            }

            player.skin = PlayerSkin(
                skin.value,
                skin.signature
            )
        }
    }

    fun PlayerLoginEvent.on() {
        setSpawningInstance(FlatWorld.instance)
        player.respawnPoint = FlatWorld.spawn(player)
        player.setGameMode(GameMode.CREATIVE)
    }
}