package sp.kx.physics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import sp.kx.math.offsetOf
import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

internal class VelocityUtilTest {
    @Test
    fun getLengthTest() {
        listOf(
            getLength(
                vs = 8.0,
                vt = 4.0,
                duration = 2.seconds,
                timeUnit = TimeUnit.SECONDS,
            ) to 12.0,
            getLength(
                vs = 60.0,
                vt = 60.0,
                duration = 90.minutes,
                timeUnit = TimeUnit.HOURS,
            ) to 90.0,
        ).forEach { (actual: Double, expected: Double) ->
            assertEquals(expected, actual)
        }
    }

    @Test
    fun getDurationTest() {
        listOf(
            getDuration(
                vs = 8.0,
                vt = 4.0,
                length = 12.0,
                timeUnit = TimeUnit.SECONDS,
            ) to 2.seconds,
            getDuration(
                vs = 60.0,
                vt = 60.0,
                length = 90.0,
                timeUnit = TimeUnit.HOURS,
            ) to 90.minutes,
        ).forEach { (actual: Duration, expected: Duration) ->
            assertEquals(expected, actual)
        }
    }

    @Test
    fun getTargetSpeedTest() {
        listOf(
            getTargetSpeed(
                vs = 8.0,
                length = 12.0,
                duration = 2.seconds,
                timeUnit = TimeUnit.SECONDS,
            ) to 4.0,
            getTargetSpeed(
                vs = 60.0,
                length = 90.0,
                duration = 90.minutes,
                timeUnit = TimeUnit.HOURS,
            ) to 60.0,
        ).forEach { (actual: Double, expected: Double) ->
            assertEquals(expected, actual)
        }
    }

    @Test
    fun getMiddleSpeedTest() {
        TODO()
    }

    @Test
    fun getLengthVelocityTest() {
        listOf(
            getLength(
                vs = velocityOf(8.0, TimeUnit.SECONDS),
                vt = velocityOf(4.0, TimeUnit.SECONDS),
                duration = 2.seconds,
            ) to 12.0,
            getLength(
                vs = velocityOf(60.0, TimeUnit.HOURS),
                vt = velocityOf(60.0, TimeUnit.HOURS),
                duration = 90.minutes,
            ) to 90.0,
        ).forEach { (actual: Double, expected: Double) ->
            assertEquals(expected, actual, 0.00000000000001)
        }
    }

    @Test
    fun getDurationVelocityTest() {
        listOf(
            getDuration(
                vs = velocityOf(8.0, TimeUnit.SECONDS),
                vt = velocityOf(4.0, TimeUnit.SECONDS),
                length = 12.0,
            ) to 2.seconds,
            getDuration(
                vs = velocityOf(60.0, TimeUnit.HOURS),
                vt = velocityOf(60.0, TimeUnit.HOURS),
                length = 90.0,
            ) to 90.minutes,
        ).forEach { (actual: Duration, expected: Duration) ->
            assertEquals(expected, actual)
        }
    }

    @Test
    fun getTargetSpeedVelocityTest() {
        listOf(
            getTargetSpeed(
                vs = velocityOf(8.0, TimeUnit.SECONDS),
                length = 12.0,
                duration = 2.seconds,
                timeUnit = TimeUnit.SECONDS,
            ) to 4.0,
            getTargetSpeed(
                vs = velocityOf(60.0, TimeUnit.HOURS),
                length = 90.0,
                duration = 90.minutes,
                timeUnit = TimeUnit.HOURS,
            ) to 60.0,
        ).forEach { (actual: Double, expected: Double) ->
            assertEquals(expected, actual, 0.00000000000001)
        }
    }
}
