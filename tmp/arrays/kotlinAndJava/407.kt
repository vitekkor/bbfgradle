//File M.java
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class M {
   public final int component1(@NotNull C $this$component1) {
      return $this$component1.getI() + 1;
   }

   public final int component2(@NotNull C $this$component2) {
      return $this$component2.getI() + 2;
   }

   @NotNull
   public final String doTest(@NotNull ArrayList l) {
      String s = "";

      int a;
      int b;
      for(Iterator var4 = l.iterator(); var4.hasNext(); s = s + a + ':' + b + ';') {
         C var3 = (C)var4.next();
         a = this.component1(var3);
         b = this.component2(var3);
      }

      return s;
   }
}


//File Main.kt


fun box(): String {
  val l = ArrayList<C>()
  l.add(C(0))
  l.add(C(1))
  l.add(C(2))
  val s = M().doTest(l)
  return if (s == "1:2;2:3;3:4;") "OK" else "fail: $s"
}



//File C.java
import kotlin.Metadata;

public final class C {
   private final int i;

   public final int getI() {
      return this.i;
   }

   public C(int i) {
      this.i = i;
   }
}
