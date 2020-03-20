//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class A {
   public final double foo(double $this$foo, double a) {
      return a;
   }

   // $FF: synthetic method
   public static double foo$default(A var0, double var1, double var3, int var5, Object var6) {
      if ((var5 & 1) != 0) {
         var3 = 1.0D;
      }

      return var0.foo(var1, var3);
   }

   @NotNull
   public final String test() {
      if (foo$default(this, 1.0D, 0.0D, 1, (Object)null) != 1.0D) {
         return "fail";
      } else {
         return this.foo(1.0D, 2.0D) != 2.0D ? "fail" : "OK";
      }
   }
}


//File Main.kt


fun box(): String  {
   return A().test()
}

