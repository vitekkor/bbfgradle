//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class A {
   public final int foo(int $this$foo, int a) {
      return a;
   }

   // $FF: synthetic method
   public static int foo$default(A var0, int var1, int var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var2 = 1;
      }

      return var0.foo(var1, var2);
   }

   @NotNull
   public final String test() {
      if (foo$default(this, 1, 0, 1, (Object)null) != 1) {
         return "fail";
      } else {
         return this.foo(1, 2) != 2 ? "fail" : "OK";
      }
   }
}


//File Main.kt


fun box(): String  {
   return A().test()
}

