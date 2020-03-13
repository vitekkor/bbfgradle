//File A.java
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   @NotNull
   private final Integer[] v;

   @NotNull
   public final Integer[] getV() {
      return this.v;
   }

   public A(@NotNull Integer[] v) {
      super();
      this.v = v;
   }

   @NotNull
   public final Integer[] component1() {
      return this.v;
   }

   @NotNull
   public final A copy(@NotNull Integer[] v) {
      return new A(v);
   }

   // $FF: synthetic method
   public static A copy$default(A var0, Integer[] var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.v;
      }

      return var0.copy(var1);
   }

   @NotNull
   public String toString() {
      return "A(v=" + Arrays.toString(this.v) + ")";
   }

   public int hashCode() {
      Integer[] var10000 = this.v;
      return var10000 != null ? Arrays.hashCode(var10000) : 0;
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof A) {
            A var2 = (A)var1;
            if (Intrinsics.areEqual(this.v, var2.v)) {
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


fun box() : String {
  val myArray = arrayOf(0, 1, 2)
  if(A(myArray) == A(arrayOf(0, 1, 2))) return "fail"
  if(A(myArray) != A(myArray)) return "fail 2"
  return "OK"
}

