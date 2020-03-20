//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   private final double x;

   public final double getX() {
      return this.x;
   }

   public A(double x) {
      this.x = x;
   }

   public final double component1() {
      return this.x;
   }

   @NotNull
   public final A copy(double x) {
      return new A(x);
   }

   // $FF: synthetic method
   public static A copy$default(A var0, double var1, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.x;
      }

      return var0.copy(var1);
   }

   @NotNull
   public String toString() {
      return "A(x=" + this.x + ")";
   }

   public int hashCode() {
      return Double.hashCode(this.x);
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof A) {
            A var2 = (A)var1;
            if (Double.compare(this.x, var2.x) == 0) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// IGNORE_BACKEND: JS_IR
// TODO: muted automatically, investigate should it be ran for JS or not
// IGNORE_BACKEND: JS

val NAN = Double.NaN

fun box(): String {
    if (A(+0.0) == A(-0.0)) return "Fail: +0.0 == -0.0"
    if (A(+0.0).hashCode() == A(-0.0).hashCode()) return "Fail: hash(+0.0) == hash(-0.0)"

    if (A(NAN) != A(NAN)) return "Fail: NaN != NaN"
    if (A(NAN).hashCode() != A(NAN).hashCode()) return "Fail: hash(NaN) != hash(NaN)"

    val s = HashSet<A>()
    for (times in 1..5) {
        s.add(A(3.14))
        s.add(A(+0.0))
        s.add(A(-0.0))
        s.add(A(-2.72))
        s.add(A(NAN))
    }

    if (A(3.14) !in s) return "Fail: 3.14 not found"
    if (A(+0.0) !in s) return "Fail: +0.0 not found"
    if (A(-0.0) !in s) return "Fail: -0.0 not found"
    if (A(-2.72) !in s) return "Fail: -2.72 not found"
    if (A(NAN) !in s) return "Fail: NaN not found"

    return if (s.size == 5) "OK" else "Fail $s"
}

