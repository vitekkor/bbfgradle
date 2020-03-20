//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

fun box(): String {
    val c = C()
    val m = c.javaClass.getMethod("foo", Array<String>::class.java)
    return if (m.isVarArgs) "OK" else "fail"
}



//File C.java
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class C {
   @JvmOverloads
   public final void foo(int bar, @NotNull String... status) {
      }

   // $FF: synthetic method
   public static void foo$default(C var0, int var1, String[] var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = 0;
      }

      var0.foo(var1, var2);
   }

   @JvmOverloads
   public final void foo(@NotNull String... status) {
      foo$default(this, 0, status, 1, (Object)null);
   }
}
