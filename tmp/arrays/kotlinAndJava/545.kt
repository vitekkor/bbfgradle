//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   private final short a;

   public final short getA() {
      return this.a;
   }

   public A(short a) {
      this.a = a;
   }

   public final short component1() {
      return this.a;
   }

   @NotNull
   public final A copy(short a) {
      return new A(a);
   }

   // $FF: synthetic method
   public static A copy$default(A var0, short var1, int var2, Object var3) {
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
      return Short.hashCode(this.a);
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof A) {
            A var2 = (A)var1;
            if (this.a == var2.a) {
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
   val v1 = A(10.toShort()).hashCode()
   val v2 = (10.toShort() as Short?)!!.hashCode()
   return if( v1 == v2 ) "OK" else "$v1 $v2"
}

