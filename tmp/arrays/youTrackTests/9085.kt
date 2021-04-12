// Original bug: KT-9453

import kotlin.reflect.KClass

annotation class Anno(val klass: KClass<*>)

@Anno(String::class)
fun foo() {}

fun assertEquals(x: Any, y: Any) {
    if (x != y) throw AssertionError("$x != $y")
}

fun main(args: Array<String>) {
    val k = ::foo.annotations.single() as Anno
    assertEquals(String::class, k.klass)
}
