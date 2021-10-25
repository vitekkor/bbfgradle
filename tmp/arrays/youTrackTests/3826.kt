// Original bug: KT-37303

val bar = 1
operator fun Double.invoke() {} // (1)

fun test() {
    val bar = 2.0
    operator fun Int.invoke() {}  // (2)
    bar() //  should resolve to (1)
}
