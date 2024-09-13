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
        val delta = 0.000000000001
        assertEquals(3600.0, velocity.scalar(TimeUnit.HOURS), delta)
        assertEquals(60.0, velocity.scalar(TimeUnit.MINUTES), delta)
        assertEquals(1.0, velocity.scalar(TimeUnit.SECONDS), delta)
    }

    @Test
    fun divTest() {
        val length = 3600.0
        val offset = vectorOf(Point.Center, length, angle = kotlin.math.PI / 4).toOffset()
        val velocity = offset / TimeUnit.HOURS
        val delta = 0.000000000001
        assertEquals(length, velocity.scalar(TimeUnit.HOURS), delta)
        assertEquals(length / 60, velocity.scalar(TimeUnit.MINUTES), delta)
        assertEquals(length / 60 / 60, velocity.scalar(TimeUnit.SECONDS), delta)
    }
}
