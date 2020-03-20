//File M.java
import java.util.ArrayList;
import java.util.Iterator;
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
   public final String doTest(@NotNull ArrayList l) {
      String s = "";

      long a;
      long b;
      for(Iterator var4 = l.iterator(); var4.hasNext(); s = s + a + ':' + b + ';') {
         Long var3 = (Long)var4.next();
         a = this.component1(var3);
         b = this.component2(var3);
      }

      return s;
   }
}


//File Main.kt


fun box(): String {
  val l = ArrayList<Long>()
  l.add(0)
  l.add(1)
  l.add(2)
  val s = M().doTest(l)
  return if (s == "1:2;2:3;3:4;") "OK" else "fail: $s"
}

