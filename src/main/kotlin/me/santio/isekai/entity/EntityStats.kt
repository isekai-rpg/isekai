package me.santio.isekai.entity

/**
 * Generic stats class for entities that can be shared
 * between entities and players.
 *
 */
open class EntityStats(
    health: Float,
    maxHealth: Float
) {
    /**
     * Constructor for when the max health is the same as the health
     * @param maxHealth the max health of the entity
     */
    constructor(maxHealth: Float) : this(maxHealth, maxHealth)

    private var health = health

    private var maxHealth = maxHealth

    fun addHealth(amount: Float) {
        health += amount

        if(health > maxHealth) {
            health = maxHealth
        }
    }

    /**
     * @return true if the entity died
     */
    fun removeHealth(amount: Float): Boolean {
        health -= amount
        return health <= 0
    }

    fun setHealth(health: Float) {
        this.health = health
    }

    fun health(): Float = health

    fun addMaxHealth(amount: Float) {
        maxHealth += amount
    }

    fun removeMaxHealth(amount: Float) {
        maxHealth -= amount

        if(maxHealth < MINIMUM_HEALTH) {
            maxHealth = MINIMUM_HEALTH
        }
    }

    fun maxHealth(): Float = maxHealth
    override fun toString(): String {
        return "EntityStats(health=$health, maxHealth=$maxHealth)"
    }

    companion object {
        const val MINIMUM_HEALTH = 1.0F
    }
}