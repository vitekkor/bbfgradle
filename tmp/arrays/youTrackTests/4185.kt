// Original bug: KT-36104

fun <T> materialize(): T = null as T
fun <T> select(x: T, y: T) = null as T

fun test(list: List<Int>?) {
    val x = select({ list?.forEach {} }, materialize<() -> Unit>()) // x is `() â Unit` in OI, `() â Unit?` in NI
}
