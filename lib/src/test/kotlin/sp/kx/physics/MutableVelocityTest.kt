package sp.kx.physics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.TimeUnit

internal class MutableVelocityTest {
    @Test
    fun scalarTest() {
        val velocity = MutableVelocity.of(
            magnitude = 3600.0,
            angle = 0.0,
            timeUnit = TimeUnit.HOURS,
        )
        assertEquals(3600.0, velocity.scalar(TimeUnit.HOURS), 0.00000000000001)
        assertEquals(60.0, velocity.scalar(TimeUnit.MINUTES), 0.00000000000001)
        assertEquals(1.0, velocity.scalar(TimeUnit.SECONDS), 0.00000000000001)
    }
}
