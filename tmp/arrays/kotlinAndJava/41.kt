//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   String getS();
}


//File Main.kt
// !LANGUAGE: +NewInference
// IGNORE_BACKEND_FIR: JVM_IR
// WITH_RUNTIME
// KJS_WITH_FULL_RUNTIME
// FULL_JDK
// ISSUE: KT-35967

fun test(list: List<A>) {
    sequence {
        yieldAll(list.map { it.s })
    }
}

fun box(): String = "OK"

