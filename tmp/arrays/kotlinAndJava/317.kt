//File B.java
import kotlin.Metadata;

public final class B {
   private final void foo(int i) {
   }

   // $FF: synthetic method
   static void foo$default(B var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = 1;
      }

      var0.foo(var1);
   }

   public final void f() {
      this.foo(2);
   }
}


//File Main.kt


fun box(): String {
    return "OK"
}

// 0 INVOKEVIRTUAL
// 2 INVOKESPECIAL B\.foo

