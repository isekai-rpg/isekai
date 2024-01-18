package me.santio.isekai.particle

import me.santio.isekai.helper.minestom.vec
import net.minestom.server.coordinate.Vec
import kotlin.math.cos
import kotlin.math.sin

/**
 * Assists with providing particle shapes.
 */
object ShapeHelper {

    /**
     * Creates a 3D Sphere.
     * @param radius The radius of the sphere.
     * @param density The density of the sphere, 100 being the densest, 1 being the least dense.
     * @param center The center of the sphere.
     * @return A list of vectors that form a sphere, positioned relative to the center provided,
     * or [Vec.ZERO] if none is provided.
     */
    fun getSphere(radius: Double, density: Int = 80, center: Vec = Vec.ZERO): List<Vec> {
        val list = mutableListOf<Vec>()

        if (density < 1 || density > 100) error("Density must be between 1 and 100!")
        val steps = 360 / (100 - density)

        for (i in 0..360 step steps) {
            for (j in 0..360 step steps) {
                val x = center.x + radius * sin(i.toDouble()) * cos(j.toDouble())
                val y = center.y + radius * sin(i.toDouble()) * sin(j.toDouble())
                val z = center.z + radius * cos(i.toDouble())
                list.add(vec(x, y, z))
            }
        }

        return list
    }

}