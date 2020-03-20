//File Generic.java
import kotlin.Metadata;

public final class Generic {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// IGNORE_BACKEND: NATIVE
// WITH_REFLECT

import kotlin.test.assertEquals

fun box(): String {
    val g = Generic::class
    assertEquals("Generic", g.simpleName)
    return "OK"
}

