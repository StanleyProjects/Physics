package sp.kx.physics

import sp.kx.math.MutableOffset
import sp.kx.math.angleOf
import sp.kx.math.distanceOf
import sp.kx.math.isEmpty
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.concurrent.TimeUnit
import kotlin.time.Duration

class MutableAcceleration : Acceleration {
    private val offset: MutableOffset

    constructor(
        dX: Double,
        dY: Double,
        timeUnit: TimeUnit,
    ) {
        val bN = BigDecimal(timeUnit.toNanos(1))
        offset = MutableOffset(
            dX = BigDecimal(dX)
                .divide(bN, 128, roundingMode)
                .divide(bN, 128, roundingMode)
                .toDouble(),
            dY = BigDecimal(dY)
                .divide(bN, 128, roundingMode)
                .divide(bN, 128, roundingMode)
                .toDouble(),
        )
    }

    private constructor(
        dX: Double,
        dY: Double,
    ) {
        offset = MutableOffset(
            dX = dX,
            dY = dY,
        )
    }

    override fun scalar(timeUnit: TimeUnit): Double {
        val bN = BigDecimal(timeUnit.toNanos(1))
        return BigDecimal(distanceOf(offset)).multiply(bN).multiply(bN).toDouble()
    }

    override fun angle(): Double {
        return angleOf(offset)
    }

    override fun isEmpty(): Boolean {
        return offset.isEmpty()
    }

    override fun speed(timeUnit: TimeUnit, duration: Duration): Double {
        return (distanceOf(offset) * duration.inWholeNanoseconds) * timeUnit.toNanos(1)
    }

    companion object {
        private val roundingMode = RoundingMode.DOWN

        fun of(
            magnitude: Double,
            angle: Double,
            timeUnit: TimeUnit,
        ): MutableAcceleration {
            val bN = BigDecimal(timeUnit.toNanos(1))
            val bM = BigDecimal(magnitude)
            return MutableAcceleration(
                dX = BigDecimal(kotlin.math.cos(angle))
                    .multiply(bM)
                    .divide(bN, 128, roundingMode)
                    .divide(bN, 128, roundingMode)
                    .toDouble(),
                dY = BigDecimal(kotlin.math.sin(angle))
                    .multiply(bM)
                    .divide(bN, 128, roundingMode)
                    .divide(bN, 128, roundingMode)
                    .toDouble(),
            )
        }

        fun of(
            magnitude: Double,
            timeUnit: TimeUnit,
        ): MutableAcceleration {
            val bN = BigDecimal(timeUnit.toNanos(1))
            return MutableAcceleration(
                dX = BigDecimal(magnitude)
                    .divide(bN, 128, roundingMode)
                    .divide(bN, 128, roundingMode)
                    .toDouble(),
                dY = 0.0,
            )
        }
    }
}
