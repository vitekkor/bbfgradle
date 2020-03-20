//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   private final int x;
   @NotNull
   private final String y;

   public final int getX() {
      return this.x;
   }

   @NotNull
   public final String getY() {
      return this.y;
   }

   public A(int x, @NotNull String y) {
      super();
      this.x = x;
      this.y = y;
   }

   public final int component1() {
      return this.x;
   }

   @NotNull
   public final String component2() {
      return this.y;
   }

   @NotNull
   public final A copy(int x, @NotNull String y) {
      return new A(x, y);
   }

   // $FF: synthetic method
   public static A copy$default(A var0, int var1, String var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.x;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.y;
      }

      return var0.copy(var1, var2);
   }

   @NotNull
   public String toString() {
      return "A(x=" + this.x + ", y=" + this.y + ")";
   }

   public int hashCode() {
      int var10000 = Integer.hashCode(this.x) * 31;
      String var10001 = this.y;
      return var10000 + (var10001 != null ? var10001.hashCode() : 0);
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof A) {
            A var2 = (A)var1;
            if (this.x == var2.x && Intrinsics.areEqual(this.y, var2.y)) {
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
    val arr = Array<A>(5) {
        i -> A(i, i.toString())
    }
    
    var sum = 0
    var str = ""
    
    for ((x, y) in arr) {
        sum += x
        str += y
    }
    
    return if (sum == 0+1+2+3+4 && str == "01234") "OK" else "Fail ${sum} ${str}"
}

