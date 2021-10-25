// Original bug: KT-9100

fun test2(val1: Any?, val2: Any?) {
    val value = val1 ?: val2!!
    val2.hashCode() // ??? smart cast to Any
}
fun main(args: Array<String>) {
    test2(Any(), null)
}
