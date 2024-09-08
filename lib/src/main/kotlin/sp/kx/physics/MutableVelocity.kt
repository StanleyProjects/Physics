package sp.kx.physics

import sp.kx.math.MutableOffset
import sp.kx.math.add
import sp.kx.math.angleOf
import sp.kx.math.distanceOf
import sp.kx.math.isEmpty
import java.util.concurrent.TimeUnit

class MutableVelocity(
    dX: Double,
    dY: Double,
    timeUnit: TimeUnit,
) : Velocity {
    private val offset = MutableOffset(
        dX = dX / timeUnit.toNanos(1),
        dY = dY / timeUnit.toNanos(1),
    )

    override fun scalar(timeUnit: TimeUnit): Double {
        return distanceOf(offset) * timeUnit.toNanos(1)
    }

    override fun angle(): Double {
        return angleOf(offset)
    }

    override fun isEmpty(): Boolean {
        return offset.isEmpty()
    }

    fun add(
        magnitude: Double,
        angle: Double,
        timeUnit: TimeUnit,
    ) {
        offset.add(
            dX = magnitude * kotlin.math.cos(angle) / timeUnit.toNanos(1),
            dY = magnitude * kotlin.math.sin(angle) / timeUnit.toNanos(1),
        )
    }

    fun set(
        magnitude: Double,
        angle: Double,
        timeUnit: TimeUnit,
    ) {
        offset.set(
            dX = magnitude * kotlin.math.cos(angle) / timeUnit.toNanos(1),
            dY = magnitude * kotlin.math.sin(angle) / timeUnit.toNanos(1),
        )
    }

    fun set(
        magnitude: Double,
        timeUnit: TimeUnit,
    ) {
        val angle = angleOf(offset)
        offset.set(
            dX = magnitude * kotlin.math.cos(angle) / timeUnit.toNanos(1),
            dY = magnitude * kotlin.math.sin(angle) / timeUnit.toNanos(1),
        )
    }

    fun clear() {
        // todo sp.kx.math.clear
        offset.set(
            dX = 0.0,
            dY = 0.0,
        )
    }

    companion object {
        fun of(
            magnitude: Double,
            angle: Double,
            timeUnit: TimeUnit,
        ): MutableVelocity {
            return MutableVelocity(
                dX = magnitude * kotlin.math.cos(angle),
                dY = magnitude * kotlin.math.sin(angle),
                timeUnit = timeUnit,
            )
        }

        fun of(
            magnitude: Double,
            timeUnit: TimeUnit,
        ): MutableVelocity {
            return MutableVelocity(
                dX = magnitude,
                dY = 0.0,
                timeUnit = timeUnit,
            )
        }
    }
}
