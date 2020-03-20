//File M.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class M {
   public final int component1(int $this$component1) {
      return $this$component1 + 1;
   }

   public final int component2(int $this$component2) {
      return $this$component2 + 2;
   }

   @NotNull
   public final String doTest() {
      String s = "";
      int var2 = 0;

      for(byte var3 = 2; var2 <= var3; ++var2) {
         int a = this.component1(var2);
         int b = this.component2(var2);
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

