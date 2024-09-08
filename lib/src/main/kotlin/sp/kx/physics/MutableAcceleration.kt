package sp.kx.physics

import sp.kx.math.MutableOffset
import sp.kx.math.angleOf
import sp.kx.math.distanceOf
import sp.kx.math.isEmpty
import java.util.concurrent.TimeUnit
import kotlin.time.Duration

class MutableAcceleration(
    dX: Double,
    dY: Double,
    timeUnit: TimeUnit,
) : Acceleration {
    private val offset: MutableOffset

    init {
        val nanos = timeUnit.toNanos(1)
        offset = MutableOffset(
            dX = dX / (nanos * nanos),
            dY = dY / (nanos * nanos),
        )
    }

    override fun scalar(timeUnit: TimeUnit): Double {
        val nanos = timeUnit.toNanos(1)
        return distanceOf(offset) * nanos * nanos
    }

    override fun angle(): Double {
        return angleOf(offset)
    }

    override fun isEmpty(): Boolean {
        return offset.isEmpty()
    }

    override fun speed(duration: Duration, timeUnit: TimeUnit): Double {
        return (distanceOf(offset) * duration.inWholeNanoseconds) * timeUnit.toNanos(1)
    }
}
