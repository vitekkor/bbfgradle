//File A.java
import kotlin.Metadata;

public final class A {
   public final int o() {
      return 111;
   }

   public final int k(int k) {
      return k;
   }
}


//File Main.kt


fun A.foo() = (A::o)(this) + (A::k)(this, 222)

fun box(): String {
    val result = A().foo()
    if (result != 333) return "Fail $result"
    return "OK"
}

