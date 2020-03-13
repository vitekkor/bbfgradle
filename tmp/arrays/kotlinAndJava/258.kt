//File A.java
import kotlin.Metadata;

public final class A {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// IGNORE_BACKEND: NATIVE
// WITH_REFLECT

fun box(): String {
    val klass = A::class
    return if (klass.toString() == "class A") "OK" else "Fail: $klass"
}

