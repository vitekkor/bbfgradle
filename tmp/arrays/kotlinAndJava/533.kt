//File Pair.java
import kotlin.Metadata;

public final class Pair {
   public Pair(Object a) {
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// KT-2739 Error type inferred for hashSet(Pair, Pair, Pair)

fun <T> foo(vararg ts: T): T? = null

fun box(): String {
    val v = foo(Pair(1))
    return if (v == null) "OK" else "fail"
}

