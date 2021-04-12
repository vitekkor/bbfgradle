// Original bug: KT-41254

class Inv<T>(val x: T?)

fun <R> foo(f: () -> R?): Inv<R> {
    val r = f()
    println(r) // prints null in 1.3.72 and kotlin.Unit in 1.4.0
    return Inv(r)
}

fun main() {
    val r: Inv<Unit> = foo { if (false) Unit else null }
}
