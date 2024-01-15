package me.santio.isekai.entity

/**
 * Generic stats class for entities that can be shared
 * between entities and players.
 *
 */
open class EntityStats(
    health: Double,
    maxHealth: Double
) {
    /**
     * Constructor for when the max health is the same as the health
     * @param maxHealth the max health of the entity
     */
    constructor(maxHealth: Double) : this(maxHealth, maxHealth)

    var health = health
        private set

    var maxHealth = maxHealth
        private set

    fun addHealth(amount: Double) {
        health += amount

        if(health > maxHealth) {
            health = maxHealth
        }
    }

    /**
     * @return true if the entity died
     */
    fun removeHealth(amount: Double): Boolean {
        health -= amount
        return health <= 0
    }

    fun health(): Double = health

    fun addMaxHealth(amount: Double) {
        maxHealth += amount
    }

    fun removeMaxHealth(amount: Double) {
        maxHealth -= amount

        if(maxHealth < MINIMUM_HEALTH) {
            maxHealth = MINIMUM_HEALTH
        }
    }

    fun maxHealth(): Double = maxHealth

    companion object {
        const val MINIMUM_HEALTH = 1.0
    }
}