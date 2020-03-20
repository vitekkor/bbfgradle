//File A.java
import kotlin.Metadata;

public enum A {
   ONE,
   TWO;
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
import A.ONE

operator fun A.invoke(i: Int) = i

fun box() = if (ONE(42) == 42) "OK" else "fail"

