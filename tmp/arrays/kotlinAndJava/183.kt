//File M.java
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
   public final String doTest(@NotNull C[] l) {
      String s = "";
      C[] var5 = l;
      int var6 = l.length;

      for(int var4 = 0; var4 < var6; ++var4) {
         C var3 = var5[var4];
         int a = this.component1(var3);
         int b = this.component2(var3);
         s = s + a + ':' + b + ';';
      }

      return s;
   }
}


//File Main.kt


fun box(): String {
  val l = Array<C>(3, {x -> C(x)})
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
