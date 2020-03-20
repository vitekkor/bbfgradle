//File A.java
import kotlin.Metadata;

public final class A {
   public final class Inner {
      private final int o = 111;
      private final int k = 222;

      public final int getO() {
         return this.o;
      }

      public final int getK() {
         return this.k;
      }
   }
}


//File Main.kt


fun box(): String {
    val result = (A::Inner)((::A)()).o + (A::Inner)(A()).k
    if (result != 333) return "Fail $result"
    return "OK"
}

