//File OK.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class OK {
   public static final OK INSTANCE;

   @NotNull
   public String toString() {
      return "OK";
   }

   private OK() {
   }

   static {
      OK var0 = new OK();
      INSTANCE = var0;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM
// WITH_REFLECT

// KT-9345 Type inference failure
fun Class<*>.foo(): Any? = kotlin.objectInstance


fun box(): String {
    return OK::class.java.foo().toString()
}

