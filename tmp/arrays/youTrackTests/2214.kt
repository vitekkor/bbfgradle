// Original bug: KT-28214

import java.lang.invoke.MethodHandles
import kotlin.reflect.jvm.javaMethod

interface A<T> {
    fun foo(t: T) {
        println(t)
    }
}

class B: A<Z>

inline class Z(val s: String)

fun foo(z: Z): String = z.s

fun main () {
    val a: A<Z> = B()
    MethodHandles.lookup().unreflect(a::foo.javaMethod!!).invokeExact(a, Z("OK"))
}
