package sp.kx.physics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.TimeUnit

internal class MutableAccelerationTest {
    @Test
    fun scalarTest() {
        val acceleration = MutableAcceleration.of(
            magnitude = 3_600.0,
            angle = 0.0,
            timeUnit = TimeUnit.HOURS,
        )
        val delta = 0.000000000001
        assertEquals(3_600.0 / TimeUnit.HOURS.toHours(1) / TimeUnit.HOURS.toHours(1), acceleration.scalar(TimeUnit.HOURS), delta)
        assertEquals(3_600.0 / TimeUnit.HOURS.toMinutes(1) / TimeUnit.HOURS.toMinutes(1), acceleration.scalar(TimeUnit.MINUTES), delta)
        assertEquals(3_600.0 / TimeUnit.HOURS.toSeconds(1) / TimeUnit.HOURS.toSeconds(1), acceleration.scalar(TimeUnit.SECONDS), delta)
        assertEquals(3_600.0 / TimeUnit.HOURS.toMillis(1) / TimeUnit.HOURS.toMillis(1), acceleration.scalar(TimeUnit.MILLISECONDS), delta)
    }
}
