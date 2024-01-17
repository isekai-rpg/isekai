package me.santio.isekai.entity

import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.EntityType

abstract class IsekaiCreature(
    entityType: EntityType,
    final override val stats: EntityStats
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

    override fun getHealth(): Float = stats.health()

    override fun setHealth(health: Float) {
        stats.setHealth(health)

        if(stats.health() <= 0 && !isDead())  {
            kill()
        }
    }

    override fun getMaxHealth(): Float = stats.maxHealth()


    @Suppress("SENSELESS_COMPARISON") // NUH UH!
    override fun heal() {
        // edge case where super constructor of
        // [LivingEntity] calls [heal] before [stats] is initialized - tech
        if(stats == null) {
            return
        }

        stats.setHealth(stats.maxHealth())
    }
}