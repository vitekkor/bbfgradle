// Original bug: KT-34629

import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

class MyTest
{
    @Test
    internal fun myTest()
    {
        assertAll(
                Executable { assertEquals(4, 2 + 2) },
                Executable { assertEquals(5, 2 + 3) }
                 )
    }
}
