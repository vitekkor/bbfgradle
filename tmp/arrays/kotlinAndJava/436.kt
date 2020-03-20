//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   private int x;
   @NotNull
   private String y;

   public final int getX() {
      return this.x;
   }

   public final void setX(int var1) {
      this.x = var1;
   }

   @NotNull
   public final String getY() {
      return this.y;
   }

   public final void setY(@NotNull String var1) {
      this.y = var1;
   }

   public A(int x, @NotNull String y) {
      super();
      this.x = x;
      this.y = y;
   }

   public final int component1() {
      return this.x;
   }

   @NotNull
   public final String component2() {
      return this.y;
   }

   @NotNull
   public final A copy(int x, @NotNull String y) {
      return new A(x, y);
   }

   // $FF: synthetic method
   public static A copy$default(A var0, int var1, String var2, int var3, Object var4) {
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
      int var10000 = Integer.hashCode(this.x) * 31;
      String var10001 = this.y;
      return var10000 + (var10001 != null ? var10001.hashCode() : 0);
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof A) {
            A var2 = (A)var1;
            if (this.x == var2.x && Intrinsics.areEqual(this.y, var2.y)) {
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
    val a = A(21, "K")
    if (a.component1() != 21 || a.component2() != "K") return "Fail"
    a.x *= 2
    a.y = "O" + a.component2()
    return if (a.component1() == 42) a.component2() else a.component1().toString()
}

