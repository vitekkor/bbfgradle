// Original bug: KT-32026

import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType

class C {
    fun foo(s: String) {
        println(s)
    }
}

fun main() {
    val mh = MethodHandles.lookup().findVirtual(
        C::class.java, "foo",
        MethodType.methodType(Void.TYPE, String::class.java)
    )

    // Problem here:
    // invokevirtual java/lang/invoke/MethodHandle.invokeExact:(LC;Ljava/lang/String;)Ljava/lang/Object;
    mh.invokeExact(C(), "Hello")
}
