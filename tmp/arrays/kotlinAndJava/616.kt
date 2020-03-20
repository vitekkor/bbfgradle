//File A.java
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   @Nullable
   private final Integer[] x;
   @Nullable
   private final int[] y;

   @Nullable
   public final Integer[] getX() {
      return this.x;
   }

   @Nullable
   public final int[] getY() {
      return this.y;
   }

   public A(@Nullable Integer[] x, @Nullable int[] y) {
      this.x = x;
      this.y = y;
   }

   @Nullable
   public final Integer[] component1() {
      return this.x;
   }

   @Nullable
   public final int[] component2() {
      return this.y;
   }

   @NotNull
   public final A copy(@Nullable Integer[] x, @Nullable int[] y) {
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
// IGNORE_BACKEND_FIR: JVM_IR
// IGNORE_BACKEND: JS_IR
// TODO: muted automatically, investigate should it be ran for JS or not
// IGNORE_BACKEND: JS

fun box(): String {
    var ts = A(Array<Int>(2, {it}), IntArray(3)).toString()
    if(ts != "A(x=[0, 1], y=[0, 0, 0])") return ts

    ts = A(null, IntArray(3)).toString()
    if(ts != "A(x=null, y=[0, 0, 0])") return ts

    ts = A(null, null).toString()
    if(ts != "A(x=null, y=null)") return ts

    return "OK"
}

