//File A.java
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   @NotNull
   private final Unit x;

   @NotNull
   public final Unit getX() {
      return this.x;
   }

   public A(@NotNull Unit x) {
      super();
      this.x = x;
   }

   public final void component1() {
   }

   @NotNull
   public final A copy(@NotNull Unit x) {
      return new A(x);
   }

   // $FF: synthetic method
   public static A copy$default(A var0, Unit var1, int var2, Object var3) {
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
      Unit var10000 = this.x;
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
    val a = A(Unit)
    return if ("$a" == "A(x=kotlin.Unit)") "OK" else "$a"
}

