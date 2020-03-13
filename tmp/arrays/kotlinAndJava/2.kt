//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   String f();
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_REFLECT

inline fun foo(): A {
    return object : A {
        override fun f(): String {
            return "OK"
        }
    }
}

fun box(): String {
    val y = foo()

    val enclosing = y.javaClass.getEnclosingMethod()
    if (enclosing?.getName() != "foo") return "method: $enclosing"

    return y.f()
}

