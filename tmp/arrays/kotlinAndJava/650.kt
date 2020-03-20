//File Test.java
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

public final class Test {
   public final long getFoo(long $this$foo) {
      return $this$foo + 1L;
   }

   public final int getFoo(int $this$foo) {
      return $this$foo + 1;
   }

   @Nullable
   public final Long testLong() {
      Long s = 10L;
      return this.getFoo(s);
   }

   @Nullable
   public final Integer testInt() {
      Integer s = 11;
      return this.getFoo(s);
   }
}


//File Main.kt


fun box(): String {
    val s = Test()

    if (s.testLong() != 11.toLong()) return "fail 1"

    if (s.testInt() != 12) return "fail 1"

    return "OK"
}

