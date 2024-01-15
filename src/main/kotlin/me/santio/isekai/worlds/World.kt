package me.santio.isekai.worlds

import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import net.minestom.server.instance.AnvilLoader
import net.minestom.server.instance.Instance
import net.minestom.server.instance.InstanceManager
import net.minestom.server.world.DimensionType

abstract class World(
    private val file: String? = null,
) {
    private var instance: Instance? = null
    protected open val dimension: DimensionType = DimensionType.OVERWORLD

    fun get(manager: InstanceManager): Instance {
        if (instance != null) return instance!!

        instance = if (file != null) {
            manager.createInstanceContainer(dimension, AnvilLoader(file))
        } else manager.createInstanceContainer(dimension)

        generate(instance!!)
        return instance!!
    }

    fun save() {
        instance?.saveChunksToStorage()
    }

    fun isLoaded(): Boolean {
        return instance != null
    }

    protected abstract fun generate(instance: Instance)
    abstract fun spawn(player: Player): Pos

}