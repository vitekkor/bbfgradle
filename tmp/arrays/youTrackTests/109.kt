// Original bug: KT-22752

import kotlin.reflect.KProperty

class Delegate<T>(var inner: T) {
    operator fun getValue(t: Any?, p: KProperty<*>): T = inner
    operator fun setValue(t: Any?, p: KProperty<*>, i: T) { inner = i }
}

val del = Delegate("zzz")

class A {
    var prop: String by del
}

fun main(args: Array<String>) {
    val a = A()

    (del as Delegate<Int>).inner = 10

    // Fails with runtime/src/main/cpp/Console.cpp:29: runtime assert: Must use a string
    // instead of ClassCastException
    println(a.prop)

    println("OK")
}
