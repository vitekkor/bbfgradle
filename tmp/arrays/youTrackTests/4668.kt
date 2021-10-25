// Original bug: KT-22392

fun overloaded(x: Any) { println("overloaded(Any)") }
fun overloaded(x: Int) { println("overloaded(Int)") }

fun main() {
    val map = mapOf(Pair("foo", 1))
    map.forEach { (_, value: Any) -> overloaded(value) }
    for ((_, value: Any) in map) {
        overloaded(value)
    }
}
