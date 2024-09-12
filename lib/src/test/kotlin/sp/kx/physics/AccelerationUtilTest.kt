package sp.kx.physics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import sp.kx.math.toString
import java.util.concurrent.TimeUnit
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

internal class AccelerationUtilTest {
    @Test
    fun getAccelerationTest() {
        listOf(
            getAcceleration(
                vs = 0.0,
                vt = 2.0,
                duration = 2.seconds,
                timeUnit = TimeUnit.SECONDS,
            ) to 1.0,
            getAcceleration(
                vs = 42.0,
                vt = 53.0,
                duration = 2.hours,
                timeUnit = TimeUnit.HOURS,
            ) to 5.5,
        ).forEach { (actual, expected) ->
            assertEquals(expected, actual)
        }
    }

    @Test
    fun getAccelerationVelocityTest() {
        val delta = 0.000000000001
        listOf(
            getAcceleration(
                vs = velocityOf(magnitude = 0.0, timeUnit = TimeUnit.SECONDS),
                vt = velocityOf(magnitude = 2.0, timeUnit = TimeUnit.SECONDS),
                duration = 2.seconds,
                timeUnit = TimeUnit.SECONDS,
            ) to 1.0,
            getAcceleration(
                vs = velocityOf(magnitude = 42.0, timeUnit = TimeUnit.HOURS),
                vt = velocityOf(magnitude = 53.0, timeUnit = TimeUnit.HOURS),
                duration = 2.hours,
                timeUnit = TimeUnit.HOURS,
            ) to 5.5,
        ).forEach { (actual, expected) ->
            assertEquals(expected, actual, delta)
        }
    }

    @Test
    fun getLengthTest() {
        val delta = 0.000000000001
        listOf(
            getLength(
                vs = 0.0,
                vt = 3.0,
                a = 1.0,
            ) to 4.5,
        ).forEach { (actual, expected) ->
            assertEquals(expected, actual, delta)
        }
    }

    @Test
    fun getTargetSpeedTest() {
        val delta = 0.000000000001
        listOf(
            getTargetSpeed(
                vs = 0.0,
                length = 4.5,
                a = 1.0,
            ) to 3.0,
        ).forEach { (actual, expected) ->
            assertEquals(expected, actual, delta)
        }
    }
}
