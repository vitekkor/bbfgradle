//File Test.java
import kotlin.Metadata;

public enum Test {
   A,
   B,
   OTHER;
}


//File Main.kt


fun peek() = Test.A

fun box(): String {
    val x = when (val type = peek()) {
        Test.A -> "OK"
        else -> "other"
    }
    return x
}

