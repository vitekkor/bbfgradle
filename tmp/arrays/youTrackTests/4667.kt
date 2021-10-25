// Original bug: KT-22392

fun overloaded(x: Any) { println("overloaded(Any)") }
fun overloaded(x: Int) { println("overloaded(Int)") }

fun testOverloadResolution(a: Pair<Int, Int>) {
    val (a0: Any, _) = a
    overloaded(a0)
}
