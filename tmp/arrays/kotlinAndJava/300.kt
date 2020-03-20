//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class A {
   public final double foo(double $this$foo, double a, double b) {
      return a + b;
   }

   // $FF: synthetic method
   public static double foo$default(A var0, double var1, double var3, double var5, int var7, Object var8) {
      if ((var7 & 1) != 0) {
         var3 = 1.0D;
      }

      if ((var7 & 2) != 0) {
         var5 = 1.0D;
      }

      return var0.foo(var1, var3, var5);
   }

   @NotNull
   public final String test() {
      if (foo$default(this, 1.0D, 0.0D, 0.0D, 3, (Object)null) != 2.0D) {
         return "fail";
      } else if (this.foo(1.0D, 2.0D, 2.0D) != 4.0D) {
         return "fail";
      } else if (foo$default(this, 1.0D, 2.0D, 0.0D, 2, (Object)null) != 3.0D) {
         return "fail";
      } else {
         return foo$default(this, 1.0D, 0.0D, 2.0D, 1, (Object)null) != 3.0D ? "fail" : "OK";
      }
   }
}


//File Main.kt


fun box(): String  {
    return A().test()
}

