// Original bug: KT-35226

import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType

object C {
    @JvmStatic
    fun foo(vararg s: Any): Any = "${s[0]}${s[1]}"
}

fun main() {
    val mh = MethodHandles.lookup().findStatic(C::class.java, "foo", MethodType.methodType(Any::class.java, Array<Any>::class.java))
    println(mh.invokeExact(*arrayOf("A", "B")))
}

