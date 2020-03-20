//File Test.java
import kotlin.Metadata;

public final class Test {
   public final int getInnerGetter(int $this$innerGetter) {
      return $this$innerGetter;
   }

   public final int test() {
      int i = 1;
      return this.getInnerGetter(i) != 1 ? 0 : 1;
   }
}


//File Main.kt


fun box(): String {
    if (Test().test() != 1) return "inner getter or setter failed"
    return "OK"
}

