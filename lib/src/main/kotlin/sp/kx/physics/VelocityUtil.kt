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

/**
 * ```
 * m1        m2
 * * - - - > *
 * ```
 */
fun collide(
    mv1: MutableVelocity,
    m1: Double,
    f1: Double,
    mv2: MutableVelocity,
    m2: Double,
    fi: Double,
) {
    val v1 = mv1.scalar(TimeUnit.NANOSECONDS)
    val cosFi = kotlin.math.cos(fi)
    val cosF1Fi = kotlin.math.cos(f1 - fi)
    val sinFi = kotlin.math.sin(fi)
    val sinF1Fi = kotlin.math.sin(f1 - fi)
    val v1t = v1 * cosF1Fi * (m1 - m2)
    val ms = m1 + m2
    val v1x = v1t * cosFi / ms + v1 * sinF1Fi * kotlin.math.cos(fi + kotlin.math.PI / 2)
    val v1y = v1t * sinFi / ms + v1 * sinF1Fi * kotlin.math.sin(fi + kotlin.math.PI / 2)
    mv1.offset(
        dX = v1x,
        dY = v1y,
        timeUnit = TimeUnit.NANOSECONDS,
    )
    val v2t = 2 * m1 * v1 * cosF1Fi
    mv2.offset(
        dX = v2t * cosFi / ms,
        dY = v2t * sinFi / ms,
        timeUnit = TimeUnit.NANOSECONDS,
    )
}
