//File Test.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Test {
   private final double z1;
   @Nullable
   private final Double z2;

   public final double getZ1() {
      return this.z1;
   }

   @Nullable
   public final Double getZ2() {
      return this.z2;
   }

   public Test(double z1, @Nullable Double z2) {
      this.z1 = z1;
      this.z2 = z2;
   }

   public final double component1() {
      return this.z1;
   }

   @Nullable
   public final Double component2() {
      return this.z2;
   }

   @NotNull
   public final Test copy(double z1, @Nullable Double z2) {
      return new Test(z1, z2);
   }

   // $FF: synthetic method
   public static Test copy$default(Test var0, double var1, Double var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = var0.z1;
      }

      if ((var4 & 2) != 0) {
         var3 = var0.z2;
      }

      return var0.copy(var1, var3);
   }

   @NotNull
   public String toString() {
      return "Test(z1=" + this.z1 + ", z2=" + this.z2 + ")";
   }

   public int hashCode() {
      int var10000 = Double.hashCode(this.z1) * 31;
      Double var10001 = this.z2;
      return var10000 + (var10001 != null ? var10001.hashCode() : 0);
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof Test) {
            Test var2 = (Test)var1;
            if (Double.compare(this.z1, var2.z1) == 0 && Intrinsics.areEqual(this.z2, var2.z2)) {
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
    val x = Test(Double.NaN, Double.NaN)
    val y = Test(Double.NaN, Double.NaN)

    return if (x == y) "OK" else "fail"
}

