package sp.kx.physics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import sp.kx.math.Point
import sp.kx.math.toOffset
import sp.kx.math.vectorOf
import java.util.concurrent.TimeUnit

internal class MutableVelocityTest {
    @Test
    fun scalarTest() {
        val velocity = MutableVelocity.of(
            magnitude = 3600.0,
            angle = 0.0,
            timeUnit = TimeUnit.HOURS,
        )
        val delta = powers[13]
        assertEquals(3600.0, velocity.scalar(TimeUnit.HOURS), delta)
        assertEquals(60.0, velocity.scalar(TimeUnit.MINUTES), delta)
        assertEquals(1.0, velocity.scalar(TimeUnit.SECONDS), delta)
    }

    @Test
    fun divTest() {
        val length = 3600.0
        val offset = vectorOf(Point.Center, length, angle = kotlin.math.PI / 4).toOffset()
        val velocity = offset / TimeUnit.HOURS
        val delta = powers[11]
        assertEquals(length, velocity.scalar(TimeUnit.HOURS), delta)
        assertEquals(length / 60, velocity.scalar(TimeUnit.MINUTES), delta)
        assertEquals(length / 60 / 60, velocity.scalar(TimeUnit.SECONDS), delta)
    }

    companion object {
        val powers = arrayOf<Double>(
            /* 00|01 */ 0.1,
            /* 01|02 */ 0.01,
            /* 02|03 */ 0.001,
            /* 03|04 */ 0.0001,
            /* 04|05 */ 0.00001,
            /* 05|06 */ 0.000001,
            /* 06|07 */ 0.0000001,
            /* 07|08 */ 0.00000001,
            /* 08|09 */ 0.000000001,
            /* 09|10 */ 0.0000000001,
            /* 10|11 */ 0.00000000001,
            /* 11|12 */ 0.000000000001,
            /* 12|13 */ 0.0000000000001,
            /* 13|14 */ 0.00000000000001,
        )
    }
}
