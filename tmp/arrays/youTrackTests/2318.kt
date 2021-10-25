// Original bug: KT-32484

import kotlin.reflect.*

fun test(k: KClass<*>, a: Any) {
    println(k.toString())
    println(k.hashCode())
    val c = a::class
    println(c.toString())
    println(c.hashCode())
    println()
}

fun main(args: Array<String>){
test(1::class, 1)
test(1L::class, 1L)
test(1.0::class, 1.0)
test(1.1::class, 1.1)
test(1.0f::class, 1.0f)
test(1.1f::class, 1.1f)
test(""::class, "")
test({}::class, {})
test(A()::class, A())
}

class A
