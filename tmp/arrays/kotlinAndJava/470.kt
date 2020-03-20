//File Klass.java
import kotlin.Metadata;

public final class Klass {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// IGNORE_BACKEND: NATIVE

// WITH_RUNTIME

import kotlin.test.assertNotNull

fun box(): String {
    assertNotNull(Int::class)
    assertNotNull(String::class)
    assertNotNull(Klass::class)
    assertNotNull(Error::class)

    return "OK"
}

