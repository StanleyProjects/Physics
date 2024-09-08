package sp.kx.physics

import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.Duration.Companion.nanoseconds

fun getLength(
    vs: Double,
    vx: Double,
    duration: Duration,
    timeUnit: TimeUnit,
): Double {
    return (vs + vx) / timeUnit.toNanos(1) * duration.inWholeNanoseconds / 2
}

fun getDuration(
    vs: Double,
    vx: Double,
    length: Double,
    timeUnit: TimeUnit,
): Duration {
    return (2 * length / (vs + vx) * timeUnit.toNanos(1)).nanoseconds
}

fun getTargetSpeed(
    vs: Double,
    length: Double,
    duration: Duration,
    timeUnit: TimeUnit,
): Double {
    return 2 * length / duration.inWholeNanoseconds * timeUnit.toNanos(1) - vs
}

fun getLength(
    vs: Velocity,
    vx: Velocity,
    duration: Duration,
): Double {
    return (vs.scalar(TimeUnit.NANOSECONDS) + vx.scalar(TimeUnit.NANOSECONDS)) * duration.inWholeNanoseconds / 2
}

fun getDuration(
    vs: Velocity,
    vx: Velocity,
    length: Double,
): Duration {
    return (2 * length / (vs.scalar(TimeUnit.NANOSECONDS) + vx.scalar(TimeUnit.NANOSECONDS))).nanoseconds
}

fun getTargetSpeed(
    vs: Velocity,
    length: Double,
    duration: Duration,
    timeUnit: TimeUnit,
): Double {
    return (2 * length / duration.inWholeNanoseconds - vs.scalar(TimeUnit.NANOSECONDS)) * timeUnit.toNanos(1)
}
