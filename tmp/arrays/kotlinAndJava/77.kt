//File A.java
import kotlin.Metadata;

public enum A {
   ONE,
   TWO;

   public final int invoke(int i) {
      return i;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
import A.ONE

fun box() = if (ONE(42) == 42) "OK" else "fail"

