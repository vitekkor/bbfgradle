//File A.java
import kotlin.Metadata;

public final class A {
   private final int prop;

   public final int getProp() {
      return this.prop;
   }

   public A(boolean arg) {
      if (arg) {
         this.prop = 1;
      } else {
         this.prop = 2;
      }
   }
}


//File Main.kt


fun box(): String {
    val a1 = A(true)
    if (a1.prop != 1) return "fail1: ${a1.prop}"
    val a2 = A(false)
    if (a2.prop != 2) return "fail2: ${a2.prop}"
    return "OK"
}

