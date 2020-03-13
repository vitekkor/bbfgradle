//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   @NotNull
   private String string;

   @NotNull
   public final String getString() {
      return this.string;
   }

   public final void setString(@NotNull String var1) {
      this.string = var1;
   }

   public A(@NotNull String string) {
      super();
      this.string = string;
   }

   @NotNull
   public final String component1() {
      return this.string;
   }

   @NotNull
   public final A copy(@NotNull String string) {
      return new A(string);
   }

   // $FF: synthetic method
   public static A copy$default(A var0, String var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.string;
      }

      return var0.copy(var1);
   }

   @NotNull
   public String toString() {
      return "A(string=" + this.string + ")";
   }

   public int hashCode() {
      String var10000 = this.string;
      return var10000 != null ? var10000.hashCode() : 0;
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof A) {
            A var2 = (A)var1;
            if (Intrinsics.areEqual(this.string, var2.string)) {
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
    val a = A("Fail")
    a.string = "OK"
    val (result) = a
    return result
}

