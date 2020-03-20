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
   public final String foo(@NotNull String o, @NotNull String k) {
      return o + k;
   }

   // $FF: synthetic method
   public static String foo$default(C var0, String var1, String var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = "O";
      }

      if ((var3 & 2) != 0) {
         var2 = "K";
      }

      return var0.foo(var1, var2);
   }

   @JvmOverloads
   @NotNull
   public final String foo(@NotNull String o) {
      return foo$default(this, o, (String)null, 2, (Object)null);
   }

   @JvmOverloads
   @NotNull
   public final String foo() {
      return foo$default(this, (String)null, (String)null, 3, (Object)null);
   }
}
