//File A.java
import kotlin.Metadata;

public final class A {
   private final int x;

   public final int getX() {
      return this.x;
   }

   public A(int x) {
      this.x = x;
   }
}


//File Main.kt


operator fun A.compareTo(other: A) = x.compareTo(other.x)

fun checkLess(x: A, y: A) = when {
    x >= y    -> "Fail $x >= $y"
    !(x < y)  -> "Fail !($x < $y)"
    !(x <= y) -> "Fail !($x <= $y)"
    x > y     -> "Fail $x > $y"
    x.compareTo(y) >= 0 -> "Fail $x.compareTo($y) >= 0"
    else -> "OK"
}

fun box() = checkLess(A(0), A(1))

