// Original bug: KT-34358

import org.junit.jupiter.api.Test

internal class KotlinJunit5Test {

    @Test
    fun dummyTest() {
        // Running test from gutter on line 6 will run the whole test class
    }


    @Test
    fun dummyTest2() {
        
    }

}