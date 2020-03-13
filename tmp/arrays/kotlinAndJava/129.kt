//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   private int x;
   @Nullable
   private final Integer z;

   public final int getX() {
      return this.x;
   }

   public final void setX(int var1) {
      this.x = var1;
   }

   @Nullable
   public final Integer getZ() {
      return this.z;
   }

   public A(int x, @Nullable Integer z) {
      this.x = x;
      this.z = z;
   }

   public final int component1() {
      return this.x;
   }

   @Nullable
   public final Integer component2() {
      return this.z;
   }

   @NotNull
   public final A copy(int x, @Nullable Integer z) {
      return new A(x, z);
   }

   // $FF: synthetic method
   public static A copy$default(A var0, int var1, Integer var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.x;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.z;
      }

      return var0.copy(var1, var2);
   }

   @NotNull
   public String toString() {
      return "A(x=" + this.x + ", z=" + this.z + ")";
   }

   public int hashCode() {
      int var10000 = Integer.hashCode(this.x) * 31;
      Integer var10001 = this.z;
      return var10000 + (var10001 != null ? var10001.hashCode() : 0);
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof A) {
            A var2 = (A)var1;
            if (this.x == var2.x && Intrinsics.areEqual(this.z, var2.z)) {
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
    val a = A(1, null)
    if("$a" != "A(x=1, z=null)") return "$a"
    return "OK"
}

