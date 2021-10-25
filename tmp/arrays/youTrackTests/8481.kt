// Original bug: KT-21072

fun main(args : Array<String>) {
    val testObject = Test()
    testObject.testFunction() // Line 3
}

class Test {
    inline fun testFunction(): Unit = null!! // Line 7
}
// Line 9 (empty)
