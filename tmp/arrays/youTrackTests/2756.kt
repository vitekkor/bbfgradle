// Original bug: KT-40746

fun select(vararg x: Int) = x[0]
fun select(vararg x: Number) = x[0]

fun <T: Number> materializeSubtypeOfNumber(): T = null as T

fun test(a: Number) {
    val x = select(materializeSubtypeOfNumber()) // `materializeSubtypeOfNumber()` is Int inside select as Int is the most specific, there is no other restrictions
    val y = select(a, materializeSubtypeOfNumber()) // `materializeSubtypeOfNumber()` is Number inside select as there is a lower constraint from the only suitable `select`: TypeVariable(T) >: Number
}
