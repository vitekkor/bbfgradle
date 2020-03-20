//File A.java
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   @NotNull
   private final Integer[] x;
   @NotNull
   private final int[] y;

   @NotNull
   public final Integer[] getX() {
      return this.x;
   }

   @NotNull
   public final int[] getY() {
      return this.y;
   }

   public A(@NotNull Integer[] x, @NotNull int[] y) {
      super();
      this.x = x;
      this.y = y;
   }

   @NotNull
   public final Integer[] component1() {
      return this.x;
   }

   @NotNull
   public final int[] component2() {
      return this.y;
   }

   @NotNull
   public final A copy(@NotNull Integer[] x, @NotNull int[] y) {
      return new A(x, y);
   }

   // $FF: synthetic method
   public static A copy$default(A var0, Integer[] var1, int[] var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.x;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.y;
      }

      return var0.copy(var1, var2);
   }

   @NotNull
   public String toString() {
      return "A(x=" + Arrays.toString(this.x) + ", y=" + Arrays.toString(this.y) + ")";
   }

   public int hashCode() {
      Integer[] var10000 = this.x;
      int var1 = (var10000 != null ? Arrays.hashCode(var10000) : 0) * 31;
      int[] var10001 = this.y;
      return var1 + (var10001 != null ? Arrays.hashCode(var10001) : 0);
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof A) {
            A var2 = (A)var1;
            if (Intrinsics.areEqual(this.x, var2.x) && Intrinsics.areEqual(this.y, var2.y)) {
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


fun foo(x: Array<Int>, y: IntArray) = A(x, y)

fun box(): String {
    val a = Array<Int>(0, {0})
    val b = IntArray(0)
    val (x, y) = foo(a, b)
    return if (a == x && b == y) "OK" else "Fail"
}

