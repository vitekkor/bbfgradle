// Original bug: KT-29943

package sample

import kotlin.reflect.KProperty1

class A {
    val b: Any = Any()
    fun b(a: Any): Any = Any()
}

fun <T, R> T.get(property: KProperty1<T, R>): R =
    property.get(this)

fun main() {
    println(A().get(A::b))
}
