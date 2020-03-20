//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   public final String f() {
      return MainKt.getX() == 1 ? "OK" : "Fail " + MainKt.getX();
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
var x = 0

fun callTwice(f: () -> String): String {
    f()
    return f()
}

fun box(): String {
    return callTwice(({ x++; A() }())::f)
}

