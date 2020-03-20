//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   private final int x;

   public int hashCode() {
      return -3;
   }

   public final int getX() {
      return this.x;
   }

   public A(int x) {
      this.x = x;
   }

   public final int component1() {
      return this.x;
   }

   @NotNull
   public final A copy(int x) {
      return new A(x);
   }

   // $FF: synthetic method
   public static A copy$default(A var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.x;
      }

      return var0.copy(var1);
   }

   @NotNull
   public String toString() {
      return "A(x=" + this.x + ")";
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof A) {
            A var2 = (A)var1;
            if (this.x == var2.x) {
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


fun box(): String {
  return if (A(0).hashCode() == -3) "OK" else "fail"
}

