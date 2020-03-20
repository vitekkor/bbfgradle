//File E.java
import kotlin.Metadata;

public enum E {
   A,
   B;
}


//File Main.kt


fun foo(e: E?): String {
    val c = when (e) {
        null -> "Fail: null"
        E.B -> "OK"
        E.A -> "Fail: A"
    }
    return c
}

fun box(): String = foo(E.B)

