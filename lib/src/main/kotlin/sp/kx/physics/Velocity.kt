package sp.kx.physics

import java.util.concurrent.TimeUnit
import kotlin.time.Duration

interface Velocity {
    fun scalar(timeUnit: TimeUnit): Double
    fun angle(): Double
    fun isEmpty(): Boolean
    fun length(duration: Duration): Double
}
