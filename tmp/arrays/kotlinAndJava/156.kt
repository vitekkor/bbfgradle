//File A.java
import kotlin.Metadata;

public enum A {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_REFLECT

import kotlin.reflect.jvm.kotlinProperty

fun box(): String {
    for (field in A::class.java.getDeclaredFields()) {
        val prop = field.kotlinProperty
        if (prop != null) return "Fail, property found: $prop"
    }

    return "OK"
}

