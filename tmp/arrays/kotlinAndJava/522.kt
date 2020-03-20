//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

fun box(): String {
    val c = C()
    val m = c.javaClass.getMethod("foo", Double::class.java, Double::class.java)
    return m.invoke(c, 1.0, 2.0) as String
}



//File C.java
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class C {
   @JvmOverloads
   @NotNull
   public final String foo(double d1, double d2, @NotNull String status) {
      return d1 + d2 == 3.0D ? status : "fail";
   }

   // $FF: synthetic method
   public static String foo$default(C var0, double var1, double var3, String var5, int var6, Object var7) {
      if ((var6 & 4) != 0) {
         var5 = "OK";
      }

      return var0.foo(var1, var3, var5);
   }

   @JvmOverloads
   @NotNull
   public final String foo(double d1, double d2) {
      return foo$default(this, d1, d2, (String)null, 4, (Object)null);
   }
}
