// Original bug: KT-41008

import kotlin.reflect.KClass

open class A
class B : A()

fun foo(cl: KClass<out A>) {
    get(cl) // no type mismatch
}

fun <T : Any> get(field: KClass<T>) {
}
