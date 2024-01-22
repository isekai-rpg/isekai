package me.santio.isekai.entity.types

import me.santio.isekai.entity.AIGroupHolder
import me.santio.isekai.entity.EntityStats
import me.santio.isekai.entity.IsekaiCreature
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.Player
import net.minestom.server.entity.ai.EntityAIGroup
import net.minestom.server.entity.ai.EntityAIGroupBuilder
import net.minestom.server.entity.ai.goal.MeleeAttackGoal
import net.minestom.server.entity.ai.goal.RandomStrollGoal
import net.minestom.server.entity.ai.target.ClosestEntityTarget
import net.minestom.server.entity.ai.target.LastEntityDamagerTarget
import net.minestom.server.utils.time.TimeUnit

class EliteZombieCreature : IsekaiCreature(
    EntityType.ZOMBIE,
    EntityStats(100.0F)
), AIGroupHolder {
    override fun aiGroup(): EntityAIGroup = EntityAIGroupBuilder()
        .addTargetSelector(LastEntityDamagerTarget(this, 32F))
        .addTargetSelector(ClosestEntityTarget(this, 32.0) { ctx ->
            ctx is Player && ctx.gameMode.canTakeDamage()
        })
        .addGoalSelector(MeleeAttackGoal(this, 1.6, 20, TimeUnit.SERVER_TICK))
        .addGoalSelector(RandomStrollGoal(this, 3))
        .build()
}