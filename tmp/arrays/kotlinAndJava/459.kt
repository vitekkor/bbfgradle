//File A.java
import kotlin.Metadata;

public final class A {
   private final int foo(int $this$foo, int other) {
      return $this$foo + other;
   }

   // $FF: synthetic method
   static int foo$default(A var0, int var1, int var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var2 = 5;
      }

      return var0.foo(var1, var2);
   }

   public final class B {
      public final int bar() {
         return A.foo$default(A.this, 37, 0, 1, (Object)null);
      }
   }
}


//File Main.kt


fun box() = if (A().B().bar() == 42) "OK" else "Fail"

