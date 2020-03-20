//File Klass.java
import kotlin.Metadata;

public final class Klass {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM
// WITH_RUNTIME

import kotlin.test.assertEquals

fun box(): String {
    assertEquals("Klass", Klass::class.simpleName)
    assertEquals("Date", java.util.Date::class.simpleName)
    assertEquals("ObjectRef", kotlin.jvm.internal.Ref.ObjectRef::class.simpleName)

    return "OK"
}

