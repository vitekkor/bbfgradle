//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   private final int a;
   private final int b;

   public final int getA() {
      return this.a;
   }

   public final int getB() {
      return this.b;
   }

   public A(int a, int b) {
      this.a = a;
      this.b = b;
   }

   public final int component1() {
      return this.a;
   }

   public final int component2() {
      return this.b;
   }

   @NotNull
   public final A copy(int a, int b) {
      return new A(a, b);
   }

   // $FF: synthetic method
   public static A copy$default(A var0, int var1, int var2, int var3, Object var4) {
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
      return "A(a=" + this.a + ", b=" + this.b + ")";
   }

   public int hashCode() {
      return Integer.hashCode(this.a) * 31 + Integer.hashCode(this.b);
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof A) {
            A var2 = (A)var1;
            if (this.a == var2.a && this.b == var2.b) {
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


fun box() : String
{
    a@ val x = 1
    b@ fun a() = 2
    c@ val (z, z2) = A(1, 2)

    if (x != 1) return "fail 1"

    if (a() != 2) return "fail 2"

    if (z != 1 || z2 != 2) return "fail 3"

    return "OK"
}

