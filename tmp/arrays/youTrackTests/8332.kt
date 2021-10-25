// Original bug: KT-11586

import kotlin.reflect.KClass

@Target(AnnotationTarget.TYPE)
annotation class Ann(val klass: KClass<*>)

class A {
    fun foo(s: @Ann(A::class) String) = s
}
