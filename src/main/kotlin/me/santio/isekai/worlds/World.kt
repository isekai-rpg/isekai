package me.santio.isekai.worlds

import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import net.minestom.server.instance.Instance
import net.minestom.server.instance.InstanceManager

interface World {

    fun get(manager: InstanceManager): Instance
    fun spawn(player: Player): Pos

}