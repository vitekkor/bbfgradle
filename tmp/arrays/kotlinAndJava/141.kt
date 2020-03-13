//File A.java
import kotlin.Metadata;

public class A implements In {
}


//File B.java
import kotlin.Metadata;

public class B implements In {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

inline fun <reified T : Any> select(x: T, y: T) = T::class.java.simpleName

// This test checks mostly that no StackOverflow happens while mapping type argument of select-call (In<A & B>)
// See KT-10972
fun foo(): String = select(A(), B())

fun box(): String {
    if (foo() != "In") return "fail"
    return "OK"
}



//File In.java
import kotlin.Metadata;

public interface In {
}
