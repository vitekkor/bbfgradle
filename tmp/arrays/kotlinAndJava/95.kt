//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

fun box(): String {
    val c = C()
    val m = c.javaClass.getMethod("foo", Int::class.java, Int::class.java)
    return m.invoke(c, 1, 2) as String
}



//File C.java
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class C {
   @JvmOverloads
   @NotNull
   public final String foo(@NotNull String o, int i1, @NotNull String k, int i2) {
      return o + k;
   }

   // $FF: synthetic method
   public static String foo$default(C var0, String var1, int var2, String var3, int var4, int var5, Object var6) {
      if ((var5 & 1) != 0) {
         var1 = "O";
      }

      if ((var5 & 4) != 0) {
         var3 = "K";
      }

      return var0.foo(var1, var2, var3, var4);
   }

   @JvmOverloads
   @NotNull
   public final String foo(@NotNull String o, int i1, int i2) {
      return foo$default(this, o, i1, (String)null, i2, 4, (Object)null);
   }

   @JvmOverloads
   @NotNull
   public final String foo(int i1, int i2) {
      return foo$default(this, (String)null, i1, (String)null, i2, 5, (Object)null);
   }
}
