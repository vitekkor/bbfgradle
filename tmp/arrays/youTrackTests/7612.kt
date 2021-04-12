// Original bug: KT-26703

package test

class Zap<T>

inline val <reified T> Zap<T>.zapper
    get() = { x: Any -> x as T }

fun main(args: Array<String>) {
    println(Zap<Int>().zapper("abc"))
}
