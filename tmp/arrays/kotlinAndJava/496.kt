//File M.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class M {
   public final int component1(int $this$component1) {
      return $this$component1 + 1;
   }

   public final int component2(int $this$component2) {
      return $this$component2 + 2;
   }

   @NotNull
   public final String doTest(@NotNull Integer[] l) {
      String s = "";
      Integer[] var5 = l;
      int var6 = l.length;

      for(int var4 = 0; var4 < var6; ++var4) {
         int var3 = var5[var4];
         int a = this.component1(var3);
         int b = this.component2(var3);
         s = s + a + ':' + b + ';';
      }

      return s;
   }
}


//File Main.kt


fun box(): String {
  val l = Array<Int>(3, {x -> x})
  val s = M().doTest(l)
  return if (s == "1:2;2:3;3:4;") "OK" else "fail: $s"
}

