//File A.java
import kotlin.Metadata;

public final class A {
   public final int calc(int p) {
      return p / 2;
   }
}


//File Main.kt
fun box(): String {
    return if (call(10, A()::calc) == 5) "OK" else "fail"
}

inline fun call(p: Int, s: (Int) -> Int): Int {
    return s(p)
}

// 1 NEW A
// 1 NEW

