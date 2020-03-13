//File M.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class M {
   public final long component1(long $this$component1) {
      return $this$component1 + 1L;
   }

   public final long component2(long $this$component2) {
      return $this$component2 + (long)2;
   }

   @NotNull
   public final String doTest(@NotNull Long[] l) {
      String s = "";
      Long[] var6 = l;
      int var7 = l.length;

      for(int var5 = 0; var5 < var7; ++var5) {
         long var3 = var6[var5];
         long a = this.component1(var3);
         long b = this.component2(var3);
         s = s + a + ':' + b + ';';
      }

      return s;
   }
}


//File Main.kt


fun box(): String {
  val l = Array<Long>(3, {x -> x.toLong()})
  val s = M().doTest(l)
  return if (s == "1:2;2:3;3:4;") "OK" else "fail: $s"
}

