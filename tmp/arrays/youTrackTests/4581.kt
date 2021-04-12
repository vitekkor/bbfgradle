// Original bug: KT-35908

import kotlin.reflect.KFunction2

class Inv<T>

fun <T, A1> fun1(f: KFunction2<Inv<T>, A1, Inv<T>>) {}

fun <T> Inv<T>.notToBe(expected: T) = Inv<T>()

fun main() {
    fun1(Inv<Int>::notToBe) // Type mismatch. Required: KFunction2<Inv<Int>, Int, Inv<Int>>. Found: KFunction2<Inv<Int>, Int, Inv<T>>
}
