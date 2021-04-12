// Original bug: KT-27580

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

fun main(args: Array<String>)
{
    println("In main")
}

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
private class Test {
    @Test
    fun SomeTest()
    {
        println("Test")
    }
}
