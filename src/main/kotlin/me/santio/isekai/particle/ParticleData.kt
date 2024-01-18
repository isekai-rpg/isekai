package me.santio.isekai.particle

import net.minestom.server.coordinate.Pos
import net.minestom.server.item.ItemStack
import net.minestom.server.utils.binary.BinaryWriter
import net.minestom.server.entity.Entity as MinestomEntity
import net.minestom.server.instance.block.Block as MinestomBlock

open class ParticleData private constructor(
    private vararg val fields: Any?
) {

    fun build(): ByteArray {
        val writer = BinaryWriter()

        for (field in fields) {
            when (field) {
                null -> continue
                is Int -> writer.writeVarInt(field)
                is Float -> writer.writeFloat(field)
                is Boolean -> writer.writeBoolean(field)
                is Long -> writer.writeLong(field)
                is Double -> writer.writeDouble(field)
                is String -> writer.writeSizedString(field)
                is DustColor -> {
                    writer.writeFloat(field.red / 255f)
                    writer.writeFloat(field.green / 255f)
                    writer.writeFloat(field.blue / 255f)
                }
                is ItemStack -> {
                    writer.writeItemStack(field)
                }
                is Pos -> {
                    writer.writeDouble(field.x())
                    writer.writeDouble(field.y())
                    writer.writeDouble(field.z())
                }
                is MinestomEntity -> {
                    writer.writeVarInt(field.entityId)
                }
                else -> error("Unknown particle data type: ${field::class.simpleName}")
            }
        }

        return writer.toByteArray()
    }

    /**
     * Particle data for a block particle, applicable for:
     *
     * ```
     * minecraft:block
     * minecraft:block_marker
     * minecraft:falling_dust
     * ```
     *
     * @param block The block to use for the particle.
     * @see MinestomBlock.stateId
     */
    class Block(block: MinestomBlock): ParticleData(
        block.stateId()
    )

    /**
     * Particle data for a dust particle, applicable for:
     *
     * ```
     * minecraft:dust
     * ```
     *
     * @param color The color of the dust particle.
     * @param scale The scale of the dust particle.
     * @see DustColor
     */
    class Dust(
        color: DustColor,
        scale: Float
    ): ParticleData(
        color,
        scale
    ) {
        init {
            if (scale < 0.01 || scale > 4.0) error("Scale must be between 0.01 and 4.0")
        }
    }

    /**
     * Particle data for a dust transition particle, applicable for:
     *
     * ```
     * minecraft:dust_color_transition
     * ```
     *
     * @param from The starting color of the dust particle.
     * @param to The ending color of the dust particle.
     * @param scale The scale of the dust particle.
     * @see DustColor
     */
    class DustTransition(
        from: DustColor,
        to: DustColor,
        scale: Float
    ): ParticleData(
        from,
        scale,
        to
    )

    /**
     * Particle data for a shulk charge particle, applicable for:
     *
     * ```
     * minecraft:sculk_charge
     * ```
     *
     * @param rolls How much the particle will be rotated when displayed.
     */
    class ShulkRoll(
        rolls: Float
    ): ParticleData(
        rolls
    )

    /**
     * Particle data for a item particle, applicable for:
     *
     * ```
     * minecraft:item
     * ```
     *
     * @param item The item to use for the particle.
     */
    class Item(
        item: ItemStack
    ): ParticleData(
        item
    )

    /**
     * Particle data for a vibration particle, applicable for:
     *
     * ```
     * minecraft:vibration
     * ```
     *
     * @param source The type of the vibration source
     * @param position The position of the vibration source. (Only applicable for block vibrations)
     * @param entity The ID of the entity the vibration originated from. (Only applicable for entity vibrations)
     * @param ticks The amount of ticks it takes for the vibration to travel from its source to its destination.
     */
    @Suppress("unused")
    open class Vibration private constructor(
        source: Source,
        position: Pos?,
        entity: MinestomEntity?,
        ticks: Int
    ): ParticleData(
        source.ordinal,
        position,
        entity,
        entity?.eyeHeight,
        ticks
    ) {

        init {
            if (source == Source.BLOCK && position == null) error("Position must be specified for block vibrations!")
            if (source == Source.ENTITY && entity == null) error("Entity must be specified for entity vibrations!")
            if (position != null && entity != null) error("Position and entity cannot be specified at the same time!")
        }

        /**
         * Particle data for a vibration particle, applicable for:
         *
         * ```
         * minecraft:vibration
         * ```
         *
         * @param position The position of the vibration source. (Only applicable for block vibrations)
         * @param ticks The amount of ticks it takes for the vibration to travel from its source to its destination.
         * @see Vibration
         */
        class Block(
            position: Pos,
            ticks: Int
        ): Vibration(
            Source.BLOCK,
            position,
            null,
            ticks
        )

        /**
         * Particle data for a vibration particle, applicable for:
         *
         * ```
         * minecraft:vibration
         * ```
         *
         * @param entity The ID of the entity the vibration originated from. (Only applicable for entity vibrations)
         * @param ticks The amount of ticks it takes for the vibration to travel from its source to its destination.
         * @see Vibration
         */
        class Entity(
            entity: MinestomEntity,
            ticks: Int
        ): Vibration(
            Source.ENTITY,
            null,
            entity,
            ticks
        )
    }

    /**
     * Particle data for a shriek particle, applicable for:
     *
     * ```
     * minecraft:shriek
     * ```
     *
     * @param delay The time in ticks before the particle is displayed.
     */
    class Shriek(
        delay: Int
    ): ParticleData(
        delay
    )

    private enum class Source {
        BLOCK,
        ENTITY
    }

}