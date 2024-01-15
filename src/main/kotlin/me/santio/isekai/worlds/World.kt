package me.santio.isekai.worlds

import net.minestom.server.MinecraftServer
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import net.minestom.server.instance.AnvilLoader
import net.minestom.server.instance.Instance
import net.minestom.server.world.DimensionType

abstract class World(
    private val file: String? = null,
) {
    val instance by lazy { get() }
    protected open val dimension: DimensionType = DimensionType.OVERWORLD

    private fun get(): Instance {
        val manager = MinecraftServer.getInstanceManager()

        val instance = if (file != null) {
            manager.createInstanceContainer(dimension, AnvilLoader(file))
        } else manager.createInstanceContainer(dimension)

        generate(instance)
        return instance
    }

    fun save() {
        instance.saveChunksToStorage()
    }

    protected abstract fun generate(instance: Instance)
    abstract fun spawn(player: Player): Pos

}