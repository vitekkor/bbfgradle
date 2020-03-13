//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   @NotNull
   private final String x;
   @NotNull
   private final String y;

   @NotNull
   public final String getX() {
      return this.x;
   }

   @NotNull
   public final String getY() {
      return this.y;
   }

   public A(@NotNull String x, @NotNull String y) {
      super();
      this.x = x;
      this.y = y;
   }

   @NotNull
   public final String component1() {
      return this.x;
   }

   @NotNull
   public final String component2() {
      return this.y;
   }

   @NotNull
   public final A copy(@NotNull String x, @NotNull String y) {
      return new A(x, y);
   }

   // $FF: synthetic method
   public static A copy$default(A var0, String var1, String var2, int var3, Object var4) {
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
      return "A(x=" + this.x + ", y=" + this.y + ")";
   }

   public int hashCode() {
      String var10000 = this.x;
      int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
      String var10001 = this.y;
      return var1 + (var10001 != null ? var10001.hashCode() : 0);
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


inline fun foo(a: A, block: (A) -> String): String = block(a)

fun box() = foo(A("O", "K")) { (x, y) -> x + y }

