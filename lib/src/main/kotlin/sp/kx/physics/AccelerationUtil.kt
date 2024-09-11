package sp.kx.physics

import java.util.concurrent.TimeUnit
import kotlin.time.Duration

fun getAcceleration(
    vs: Double,
    vt: Double,
    duration: Duration,
    timeUnit: TimeUnit,
): Double {
    return (vt - vs) / duration.inWholeNanoseconds * timeUnit.toNanos(1)
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
