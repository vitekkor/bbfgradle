//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   String f();

   @NotNull
   String g();
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

import kotlin.test.assertEquals

fun foo(block: () -> String) = block()

inline fun <reified T : Any> className(): String = T::class.java.getName()

inline fun <reified T : Any> lambdaShouldBeReified(): String = foo { className<T>() }

inline fun<reified T1 : Any, reified T2 : Any> AFactory(): A = object : A {
    override fun f(): String = className<T1>()
    override fun g(): String = foo { className<T2>() }
}

fun box(): String {
    assertEquals("java.lang.String", lambdaShouldBeReified<String>())
    assertEquals("java.lang.Integer", lambdaShouldBeReified<Int>())

    val x: A = AFactory<String, Int>()

    assertEquals("java.lang.String", x.f())
    assertEquals("java.lang.Integer", x.g())

    return "OK"
}

