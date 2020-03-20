//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

fun box(): String {
    val c = C()
    val m = c.javaClass.getMethod("foo")
    return m.invoke(c) as String
}



//File C.java
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class C {
   @JvmOverloads
   @NotNull
   public final String foo(@NotNull String s) {
      return s;
   }

   // $FF: synthetic method
   public static String foo$default(C var0, String var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = "OK";
      }

      return var0.foo(var1);
   }

   @JvmOverloads
   @NotNull
   public final String foo() {
      return foo$default(this, (String)null, 1, (Object)null);
   }
}
