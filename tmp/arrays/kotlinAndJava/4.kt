//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   private final Object x;

   public final Object getX() {
      return this.x;
   }

   public A(Object x) {
      this.x = x;
   }

   public final Object component1() {
      return this.x;
   }

   @NotNull
   public final A copy(Object x) {
      return new A(x);
   }

   // $FF: synthetic method
   public static A copy$default(A var0, Object var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.x;
      }

      return var0.copy(var1);
   }

   @NotNull
   public String toString() {
      return "A(x=" + this.x + ")";
   }

   public int hashCode() {
      Object var10000 = this.x;
      return var10000 != null ? var10000.hashCode() : 0;
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof A) {
            A var2 = (A)var1;
            if (Intrinsics.areEqual(this.x, var2.x)) {
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
    val a = A(42)
    if ("$a" != "A(x=42)") return "$a"
    
    val b = A(239.toLong())
    if ("$b" != "A(x=239)") return "$b"
    
    return "OK"
}

