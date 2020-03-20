//File M.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class M {
   public final long component1(long $this$component1) {
      return $this$component1 + 1L;
   }

   public final long component2(long $this$component2) {
      return $this$component2 + (long)2;
   }

   @NotNull
   public final String doTest() {
      String s = "";
      long var2 = 0L;

      for(long var4 = 2L; var2 <= var4; ++var2) {
         long a = this.component1(var2);
         long b = this.component2(var2);
         s = s + a + ':' + b + ';';
      }

      return s;
   }
}


//File Main.kt


fun box(): String {
  val s = M().doTest()
  return if (s == "1:2;2:3;3:4;") "OK" else "fail: $s"
}

