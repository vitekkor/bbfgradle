//File A.java
import kotlin.Metadata;

public final class A {
   private int x;

   public final int getX() {
      return this.x;
   }

   public final void setX(int var1) {
      this.x = var1;
   }
}


//File Main.kt


operator fun A.plusAssign(y: Int) { x += y }
operator fun A.minusAssign(y: Int) { x -= y }
operator fun A.timesAssign(y: Int) { x *= y }
operator fun A.divAssign(y: Int) { x /= y }
operator fun A.remAssign(y: Int) { x %= y }

fun box(): String {
  val original = A()
  val a = original

  a += 1
  if (a !== original) return "Fail 1: $a !== $original"
  if (a.x != 1) return "Fail 2: ${a.x} != 1"

  a -= 2
  if (a !== original) return "Fail 3: $a !== $original"
  if (a.x != -1) return "Fail 4: ${a.x} != -1"

  a *= -10
  if (a !== original) return "Fail 5: $a !== $original"
  if (a.x != 10) return "Fail 6: ${a.x} != 10"

  a /= 3
  if (a !== original) return "Fail 7: $a !== $original"
  if (a.x != 3) return "Fail 8: ${a.x} != 3"

  a %= 2
  if (a !== original) return "Fail 9: $a !== $original"
  if (a.x != 1) return "Fail 10: ${a.x} != 1"

  return "OK"
}

