//File A.java
import kotlin.Metadata;

public final class A {
   public final int component1() {
      return 1;
   }

   public final int component2() {
      return 2;
   }
}


//File Main.kt


fun box() : String {
    val (_, b) = A()

    val (a, _) = A()

    val (`_`, c) = A()

    return if (a == 1 && b == 2 && `_` == 1 && c == 2) "OK" else "fail"
}

