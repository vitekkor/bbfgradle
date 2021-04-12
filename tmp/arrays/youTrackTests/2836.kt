// Original bug: KT-7972

open class Base<out T>
class Derive<T>(var value: T) : Base<T>()

fun main() {
    val d = Derive(0)
    val b: Base<Any> = d
    if (b is Derive) {
        b.value = "0123456789"
    }
    println(d.value + 2)
}
