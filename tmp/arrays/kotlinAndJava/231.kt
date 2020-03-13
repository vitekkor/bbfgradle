//File Main.kt


operator fun C.component1() = i + 1
operator fun C.component2() = i + 2

fun doTest(l : Array<C>): String {
    var s = ""
    for ((a, b) in l) {
      s += "$a:$b;"
    }
    return s
}

fun box(): String {
  val l = Array<C>(3, {x -> C(x)})
  val s = doTest(l)
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
