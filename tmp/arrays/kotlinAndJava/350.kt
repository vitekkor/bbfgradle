//File Main.kt


fun doTest(l : ArrayList<C>): String {
    var s = ""
    for ((a, b) in l) {
      s += {"$a:$b;"}()
    }
    return s
}

fun box(): String {
  val l = ArrayList<C>()
  l.add(C(0))
  l.add(C(1))
  l.add(C(2))
  val s = doTest(l)
  return if (s == "1:2;2:3;3:4;") "OK" else "fail: $s"
}



//File C.java
import kotlin.Metadata;

public final class C {
   private final int i;

   public final int component1() {
      return this.i + 1;
   }

   public final int component2() {
      return this.i + 2;
   }

   public final int getI() {
      return this.i;
   }

   public C(int i) {
      this.i = i;
   }
}
