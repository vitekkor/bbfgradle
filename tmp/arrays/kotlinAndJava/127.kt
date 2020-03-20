//File D.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class D {
   private final long x;
   private final char y;

   @NotNull
   public final String foo() {
      return "" + this.component1() + this.component2();
   }

   public D(long x, char y) {
      this.x = x;
      this.y = y;
   }

   private final long component1() {
      return this.x;
   }

   private final char component2() {
      return this.y;
   }

   @NotNull
   public final D copy(long x, char y) {
      return new D(x, y);
   }

   // $FF: synthetic method
   public static D copy$default(D var0, long var1, char var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = var0.x;
      }

      if ((var4 & 2) != 0) {
         var3 = var0.y;
      }

      return var0.copy(var1, var3);
   }

   @NotNull
   public String toString() {
      return "D(x=" + this.x + ", y=" + this.y + ")";
   }

   public int hashCode() {
      return Long.hashCode(this.x) * 31 + Character.hashCode(this.y);
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof D) {
            D var2 = (D)var1;
            if (this.x == var2.x && this.y == var2.y) {
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
    val d1 = D(42L, 'a')
    val d2 = D(42L, 'a')
    if (d1 != d2) return "Fail equals"
    if (d1.hashCode() != d2.hashCode()) return "Fail hashCode"
    if (d1.toString() != d2.toString()) return "Fail toString"
    if (d1.foo() != d2.foo()) return "Fail foo"
    return "OK"
}

