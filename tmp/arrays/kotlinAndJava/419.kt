//File Z.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Z {
   @NotNull
   private final String p;
   @NotNull
   private final String k;

   @NotNull
   public final String getP() {
      return this.p;
   }

   @NotNull
   public final String getK() {
      return this.k;
   }

   public Z(@NotNull String p, @NotNull String k) {
      super();
      this.p = p;
      this.k = k;
   }

   @NotNull
   public final String component1() {
      return this.p;
   }

   @NotNull
   public final String component2() {
      return this.k;
   }

   @NotNull
   public final Z copy(@NotNull String p, @NotNull String k) {
      return new Z(p, k);
   }

   // $FF: synthetic method
   public static Z copy$default(Z var0, String var1, String var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.p;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.k;
      }

      return var0.copy(var1, var2);
   }

   @NotNull
   public String toString() {
      return "Z(p=" + this.p + ", k=" + this.k + ")";
   }

   public int hashCode() {
      String var10000 = this.p;
      int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
      String var10001 = this.k;
      return var1 + (var10001 != null ? var10001.hashCode() : 0);
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof Z) {
            Z var2 = (Z)var1;
            if (Intrinsics.areEqual(this.p, var2.p) && Intrinsics.areEqual(this.k, var2.k)) {
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



fun create(p: Boolean): Z? {
    return if (p) {
        Z("O", "K")
    }
    else {
        null;
    }
}

fun test(p: Boolean): String {
    val (a, b) = create(p) ?: return "null"
    return a + b
}

fun box(): String {
    if (test(false) != "null") return "fail 1: ${test(false)}"

    return test(true)
}

