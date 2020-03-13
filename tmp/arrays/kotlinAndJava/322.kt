//File A.java
import kotlin.Metadata;

public enum A {
   ONE,
   TWO;
}


//File Main.kt


operator fun A.invoke(i: Int) = i

fun box() = if (A.ONE(42) == 42) "OK" else "fail"

