//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   private final int x;
   @Nullable
   private final Object y;
   @NotNull
   private final String z;

   public final int getX() {
      return this.x;
   }

   @Nullable
   public final Object getY() {
      return this.y;
   }

   @NotNull
   public final String getZ() {
      return this.z;
   }

   public A(int x, @Nullable Object y, @NotNull String z) {
      super();
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public final int component1() {
      return this.x;
   }

   @Nullable
   public final Object component2() {
      return this.y;
   }

   @NotNull
   public final String component3() {
      return this.z;
   }

   @NotNull
   public final A copy(int x, @Nullable Object y, @NotNull String z) {
      return new A(x, y, z);
   }

   // $FF: synthetic method
   public static A copy$default(A var0, int var1, Object var2, String var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = var0.x;
      }

      if ((var4 & 2) != 0) {
         var2 = var0.y;
      }

      if ((var4 & 4) != 0) {
         var3 = var0.z;
      }

      return var0.copy(var1, var2, var3);
   }

   @NotNull
   public String toString() {
      return "A(x=" + this.x + ", y=" + this.y + ", z=" + this.z + ")";
   }

   public int hashCode() {
      int var10000 = Integer.hashCode(this.x) * 31;
      Object var10001 = this.y;
      var10000 = (var10000 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
      String var1 = this.z;
      return var10000 + (var1 != null ? var1.hashCode() : 0);
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof A) {
            A var2 = (A)var1;
            if (this.x == var2.x && Intrinsics.areEqual(this.y, var2.y) && Intrinsics.areEqual(this.z, var2.z)) {
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
    val a = A(42, null, "OK")
    val (x, y, z) = a
    return if (x == 42 && y == null) z else "Fail"
}

