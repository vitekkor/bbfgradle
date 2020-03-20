//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public abstract class A {
   @NotNull
   public abstract String f();
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

import kotlin.test.assertEquals

inline fun<reified T : Any> foo(): A {
    return object : A() {
        override fun f(): String {
            return T::class.java.getName()
        }
    }
}

fun box(): String {
    val y = foo<String>();
    assertEquals("java.lang.String", y.f())
    return "OK"
}

