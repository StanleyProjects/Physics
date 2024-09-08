package sp.kx.physics

import java.util.concurrent.TimeUnit
import kotlin.time.Duration

interface Acceleration {
    fun scalar(timeUnit: TimeUnit): Double
    fun angle(): Double
    fun isEmpty(): Boolean
    fun speed(timeUnit: TimeUnit, duration: Duration): Double
}
