// Original bug: KT-18380

import kotlin.reflect.*
import kotlin.reflect.jvm.*
import kotlin.test.assertEquals

// fun <@kotlin.internal.OnlyInputTypes T> 
// assertEquals(expected: T, actual: T, message: kotlin.String? = null): kotlin.Unit

inline fun <reified T> f() = 1

fun g() {}

class Foo {
    inline fun <reified T> h(t: T) = 1
}

fun box(): String {
    assertEquals(::g, ::g.javaMethod!!.kotlinFunction)

    val h = Foo::class.members.single { it.name == "h" } as KFunction<*>
    assertEquals(h, h.javaMethod!!.kotlinFunction)

    return "OK"
}
