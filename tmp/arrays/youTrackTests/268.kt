// Original bug: KT-31994

fun main() {
    testSafe(Test("Tom"))
    testSafe(null)

    println("---------")

    testBug(Test("Tom"))
    testBug(null)
}

fun testBug(test: Test?) {
    println(test?.Inner()?.thing)
}

fun testSafe(test: Test?) {
    println(test?.createInner()?.thing)
}

class Test(val name: String) {
    inner class Inner {
        val thing: String
            get() = "Hello, $name"
    }

    fun createInner() = Inner()
}
