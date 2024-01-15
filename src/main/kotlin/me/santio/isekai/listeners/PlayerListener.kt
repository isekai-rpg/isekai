package me.santio.isekai.listeners

import com.github.shynixn.mccoroutine.minestom.launch
import me.santio.isekai.io.mojang.UnifiedMojangAPI
import me.santio.isekai.worlds.FlatWorld
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
        val data = mojangAPI.get(player.username)

        server.launch {
            player.skin = PlayerSkin(
                data.textures.skin.value,
                data.textures.skin.signature,
            )
        }

        player.sendMessage("Your skin was set to ${data.username}'s skin (${data.uuid})")
    }

    fun PlayerLoginEvent.on() {
        setSpawningInstance(FlatWorld.instance)
        player.respawnPoint = FlatWorld.spawn(player)
        player.setGameMode(GameMode.CREATIVE)
    }
}