//File E.java
import kotlin.Metadata;

public enum E {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_REFLECT

import kotlin.reflect.jvm.isAccessible

fun box(): String {
    try {
        val c = E::class.constructors.single()
        c.isAccessible = true
        c.call()
        return "Fail: constructing an enum class should not be allowed"
    }
    catch (e: Throwable) {
        return "OK"
    }
}

