//File N.java
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

public final class N {
   @Nullable
   public final Void foo() {
      return null;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

fun box(): String {
    val method = N::class.java.getDeclaredMethod("foo")
    if (method.returnType.name != "java.lang.Void") return "Fail: Nothing should be mapped to Void"
    
    return "OK"
}

