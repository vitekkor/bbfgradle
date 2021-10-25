// Original bug: KT-34629

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class MyTest
{
    @Test
    internal fun myTest()
    {
        assertAll(
                { assertEquals(4, 2 + 2) },
                { assertEquals(5, 2 + 3) }
                 )
    }
}
