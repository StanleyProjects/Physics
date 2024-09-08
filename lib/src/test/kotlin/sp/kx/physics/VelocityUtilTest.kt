package sp.kx.physics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.TimeUnit
import kotlin.time.Duration.Companion.seconds

internal class VelocityUtilTest {
    @Test
    fun getLengthTest() {
        val actual = getLength(
            vs = 8.0,
            vx = 4.0,
            duration = 2.seconds,
            timeUnit = TimeUnit.SECONDS,
        )
        assertEquals(12.0, actual)
    }

    @Test
    fun getDurationTest() {
        val actual = getDuration(
            vs = 8.0,
            vx = 4.0,
            length = 12.0,
            timeUnit = TimeUnit.SECONDS,
        )
        assertEquals(2.seconds, actual)
    }
}
