// Original bug: KT-42003

inline fun <reified T> materialize(): T {
    println(T::class) // what class should be here if the intersection type `{Comparable<*> & Number}` would be passed as `T`?
    return Any() as T
}

fun <T> select(x: T, y: T): T = x

fun main() {
    val y: Any? = select(if (true) 1 else 1.0, materialize()) // the first argument is `{Comparable<*> & Number}` in NI, and `Any` in OI
}

