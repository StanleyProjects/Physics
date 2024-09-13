package sp.kx.physics

import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.Duration.Companion.nanoseconds

/**
 *         duration
 * |<----->|
 * vs      vt
 * * - - - *
 * |<----->|
 *         length
 */
fun getLength(
    vs: Double,
    vt: Double,
    duration: Duration,
    timeUnit: TimeUnit,
): Double {
    return (vs + vt) / timeUnit.toNanos(1) * duration.inWholeNanoseconds / 2
}

fun getDuration(
    vs: Double,
    vt: Double,
    length: Double,
    timeUnit: TimeUnit,
): Duration {
    return (2 * length / (vs + vt) * timeUnit.toNanos(1)).nanoseconds
}

fun getTargetSpeed(
    vs: Double,
    length: Double,
    duration: Duration,
    timeUnit: TimeUnit,
): Double {
    return 2 * length / duration.inWholeNanoseconds * timeUnit.toNanos(1) - vs
}

/**
 * ```
 *                 lt
 * |<------------->|
 * vs      vm      vt
 * * - - - * - - - *
 * |<----->|
 *         lm
 * ```
 */
fun getMiddleSpeed(
    vs: Double,
    vt: Double,
    lm: Double,
    lt: Double,
): Double {
    val duration = 2 * lt / (vs + vt)
    val a = (vt - vs) / duration
    return getTargetSpeed(
        vs = vs,
        length = lm,
        a = a,
    )
}

fun getMiddleSpeed(
    vs: Velocity,
    vt: Velocity,
    lm: Double,
    lt: Double,
    timeUnit: TimeUnit,
): Double {
    val s = vs.scalar(TimeUnit.NANOSECONDS)
    val t = vt.scalar(TimeUnit.NANOSECONDS)
    val duration = 2 * lt / (s + t)
    val a = (t - s) / duration
    return getTargetSpeed(
        vs = s,
        length = lm,
        a = a,
    ) * timeUnit.toNanos(1)
}

fun getLength(
    vs: Velocity,
    vt: Velocity,
    duration: Duration,
): Double {
    return (vs.scalar(TimeUnit.NANOSECONDS) + vt.scalar(TimeUnit.NANOSECONDS)) * duration.inWholeNanoseconds / 2
}

fun getDuration(
    vs: Velocity,
    vt: Velocity,
    length: Double,
): Duration {
    return (2 * length / (vs.scalar(TimeUnit.NANOSECONDS) + vt.scalar(TimeUnit.NANOSECONDS))).nanoseconds
}

fun getTargetSpeed(
    vs: Velocity,
    length: Double,
    duration: Duration,
    timeUnit: TimeUnit,
): Double {
    return (2 * length / duration.inWholeNanoseconds - vs.scalar(TimeUnit.NANOSECONDS)) * timeUnit.toNanos(1)
}
