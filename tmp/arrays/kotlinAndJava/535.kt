//File A.java
import kotlin.Metadata;

public final class A {
   public final class Inner {
      private final int result;

      public final int getResult() {
         return this.result;
      }

      public Inner(int result) {
         this.result = result;
      }
   }
}


//File Main.kt


fun box(): String {
    val result = (A::Inner)((::A)(), 111).result + (A::Inner)(A(), 222).result
    if (result != 333) return "Fail $result"
    return "OK"
}

