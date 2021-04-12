// Original bug: KT-22288

import kotlin.reflect.KClass

inline fun foo(crossinline f: () -> Unit = {}): KClass<*> {
    val a = object {
        val a: Any? = f()
    }
    return a::class
}

fun test1() = foo()
fun test2() = foo()

fun main(args: Array<String>) {
    println(test1())
    println(test2())

    println(test1() === test2())
    println(test1() === test1())
}
