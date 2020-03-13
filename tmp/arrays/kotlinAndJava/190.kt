//File A.java
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   @NotNull
   private final int[] a;
   @NotNull
   private String[] b;

   @NotNull
   public final int[] getA() {
      return this.a;
   }

   @NotNull
   public final String[] getB() {
      return this.b;
   }

   public final void setB(@NotNull String[] var1) {
      this.b = var1;
   }

   public A(@NotNull int[] a, @NotNull String[] b) {
      super();
      this.a = a;
      this.b = b;
   }

   @NotNull
   public final int[] component1() {
      return this.a;
   }

   @NotNull
   public final String[] component2() {
      return this.b;
   }

   @NotNull
   public final A copy(@NotNull int[] a, @NotNull String[] b) {
      return new A(a, b);
   }

   // $FF: synthetic method
   public static A copy$default(A var0, int[] var1, String[] var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.a;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.b;
      }

      return var0.copy(var1, var2);
   }

   @NotNull
   public String toString() {
      return "A(a=" + Arrays.toString(this.a) + ", b=" + Arrays.toString(this.b) + ")";
   }

   public int hashCode() {
      int[] var10000 = this.a;
      int var1 = (var10000 != null ? Arrays.hashCode(var10000) : 0) * 31;
      String[] var10001 = this.b;
      return var1 + (var10001 != null ? Arrays.hashCode(var10001) : 0);
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof A) {
            A var2 = (A)var1;
            if (Intrinsics.areEqual(this.a, var2.a) && Intrinsics.areEqual(this.b, var2.b)) {
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
// TARGET_BACKEND: JVM

fun box() : String {
   if( A(intArrayOf(1,2,3),arrayOf("239")).hashCode() != 31*java.util.Arrays.hashCode(intArrayOf(0,1,2)) + "239".hashCode()) "fail"
   return "OK"
}

