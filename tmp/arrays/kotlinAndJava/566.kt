//File A.java
import kotlin.Metadata;

public class A {
}


//File B.java
import kotlin.Metadata;

public final class B extends A {
   public final int foo(int i) {
      return i;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR

fun A.test() = if (this is B) foo(42) else 0

fun box(): String {
    if (B().test() != 42) return "fail1"
    if (A().test() != 0) return "fail2"
    return "OK"
}

