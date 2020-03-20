//File T.java
import kotlin.Metadata;

public interface T {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// WITH_RUNTIME

fun box(): String {
    val a = "OK"
    val t = object : T {
        val foo by lazy {
            a
        }
    }
    return t.foo
}

