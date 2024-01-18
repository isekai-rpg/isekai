import me.santio.isekai.helper.minestom.pos
import net.minestom.server.entity.Entity

val Entity.headPosition get() = position.add(pos(0, eyeHeight, 0))