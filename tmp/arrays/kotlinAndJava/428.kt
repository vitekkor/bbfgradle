//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   private final float a;

   public final float getA() {
      return this.a;
   }

   public A(float a) {
      this.a = a;
   }

   public final float component1() {
      return this.a;
   }

   @NotNull
   public final A copy(float a) {
      return new A(a);
   }

   // $FF: synthetic method
   public static A copy$default(A var0, float var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.a;
      }

      return var0.copy(var1);
   }

   @NotNull
   public String toString() {
      return "A(a=" + this.a + ")";
   }

   public int hashCode() {
      return Float.hashCode(this.a);
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof A) {
            A var2 = (A)var1;
            if (Float.compare(this.a, var2.a) == 0) {
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
   val v1 = A(-10.toFloat()).hashCode()
   val v2 = (-10.toFloat() as Float?)!!.hashCode()
   return if( v1 == v2 ) "OK" else "$v1 $v2"
}

