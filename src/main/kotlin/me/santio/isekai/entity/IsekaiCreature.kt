package me.santio.isekai.entity

import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.EntityType

abstract class IsekaiCreature(
    entityType: EntityType,
    override val stats: EntityStats
) : EntityCreature(entityType), StatsHolder {
    init {
        build()
    }

    /**
     * Build the entity with all necessary components.
     */
    private fun build() {
        if(this is AIGroupHolder) {
            addAIGroup(aiGroup())
        }
    }
}