// Original bug: KT-36847

object Test {
    fun <T> foo(actual: T) {}
    fun <T : Number> foo(actual: T) {}
}

fun main() {
    var y: Number? = null
    y = 2
    { y = 1 }
    Test.foo(y) // "Smart cast to 'Int' is impossible, because 'y' is a local variable that is captured by a changing closure" in NI, OK in OI
}
