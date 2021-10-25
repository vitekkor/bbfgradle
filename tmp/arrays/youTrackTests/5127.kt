// Original bug: KT-34363

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

internal class KotlinJunit5Test {

    @Test
    fun `Name with parenthesis (why not?)`() {
        fail("Can re-run single test method from test result: Fails with No tests were found")
    }

    @Test
    fun simpleName() {
        // re-run single test from test result works
    }

    @Test
    fun `Name with space`() {
        // re-run single test from test result works
    }


}