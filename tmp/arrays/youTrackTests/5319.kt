// Original bug: KT-31305

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
//import kotlin.test.assertTrue

class C31305Test {
    @Test fun testLog() {
        assertTrue(true) { "Supplied message." }
    }
}
