//File A.java
import java.util.ArrayList;
import kotlin.Metadata;

public final class A extends ArrayList {
   public int getSize() {
      return super.size() + 56;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// KJS_WITH_FULL_RUNTIME
// IGNORE_BACKEND: NATIVE

fun box(): String {
    val a = A()
    if (a.size != 56) return "fail: ${a.size}"

    return "OK"
}

