//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   @Nullable
   private final Object v;

   @Nullable
   public final Object getV() {
      return this.v;
   }

   public A(@Nullable Object v) {
      this.v = v;
   }

   @Nullable
   public final Object component1() {
      return this.v;
   }

   @NotNull
   public final A copy(@Nullable Object v) {
      return new A(v);
   }

   // $FF: synthetic method
   public static A copy$default(A var0, Object var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.v;
      }

      return var0.copy(var1);
   }

   @NotNull
   public String toString() {
      return "A(v=" + this.v + ")";
   }

   public int hashCode() {
      Object var10000 = this.v;
      return var10000 != null ? var10000.hashCode() : 0;
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof A) {
            A var2 = (A)var1;
            if (Intrinsics.areEqual(this.v, var2.v)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }
}


//File Dummy.java
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

public final class Dummy {
   public boolean equals(@Nullable Object other) {
      return true;
   }
}


//File Main.kt


fun box() : String {
  val a = A(Dummy())
  val b: A? = null
  return if(a != b && b != a) "OK" else "fail"
}

