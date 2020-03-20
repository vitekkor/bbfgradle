//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   private final int x;

   @NotNull
   public final String toString(@NotNull Object other) {
      return "";
   }

   public final int getX() {
      return this.x;
   }

   public A(int x) {
      this.x = x;
   }

   public final int component1() {
      return this.x;
   }

   @NotNull
   public final A copy(int x) {
      return new A(x);
   }

   // $FF: synthetic method
   public static A copy$default(A var0, int var1, int var2, Object var3) {
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
      return Integer.hashCode(this.x);
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof A) {
            A var2 = (A)var1;
            if (this.x == var2.x) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }
}


//File B.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class B {
   private final int x;

   @NotNull
   public final String toString(@NotNull B other, @NotNull Object another) {
      return "";
   }

   public final int getX() {
      return this.x;
   }

   public B(int x) {
      this.x = x;
   }

   public final int component1() {
      return this.x;
   }

   @NotNull
   public final B copy(int x) {
      return new B(x);
   }

   // $FF: synthetic method
   public static B copy$default(B var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.x;
      }

      return var0.copy(var1);
   }

   @NotNull
   public String toString() {
      return "B(x=" + this.x + ")";
   }

   public int hashCode() {
      return Integer.hashCode(this.x);
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof B) {
            B var2 = (B)var1;
            if (this.x == var2.x) {
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
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

fun box(): String {
  A::class.java.getDeclaredMethod("toString")
  A::class.java.getDeclaredMethod("toString", Any::class.java)

  B::class.java.getDeclaredMethod("toString")
  B::class.java.getDeclaredMethod("toString", B::class.java, Any::class.java)

  return "OK"
}

