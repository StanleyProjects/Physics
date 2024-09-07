package sp.kx.physics

import java.util.concurrent.TimeUnit

interface Velocity {
    fun scalar(timeUnit: TimeUnit): Double
    fun angle(): Double
    fun isEmpty(): Boolean
}
