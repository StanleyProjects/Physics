package sp.kx.physics

import java.util.concurrent.TimeUnit
import kotlin.time.Duration

fun getAcceleration(
    vs: Double,
    vt: Double,
    duration: Duration,
    timeUnit: TimeUnit,
): Double {
    return (vt - vs) /
        duration.inWholeNanoseconds *
        timeUnit.toNanos(1)
}

fun getAcceleration(
    vs: Velocity,
    vt: Velocity,
    duration: Duration,
    timeUnit: TimeUnit,
): Double {
    return (vt.scalar(TimeUnit.NANOSECONDS) - vs.scalar(TimeUnit.NANOSECONDS)) /
        duration.inWholeNanoseconds *
        timeUnit.toNanos(1) *
        timeUnit.toNanos(1)
}

/**
 *                 lt
 * |<------------->|
 * vs              vt
 * * - - - - - - - *
 */
fun getLength(
    vs: Double,
    vt: Double,
    a: Double,
): Double {
    return (vt * vt - vs * vs) / (2 * a)
}

/**
 *                 lt
 * |<------------->|
 * vs              vt
 * * - - - - - - - *
 */
fun getTargetSpeed(
    vs: Double,
    length: Double,
    a: Double,
): Double {
    return kotlin.math.sqrt(2 * length * a + vs * vs)
}
