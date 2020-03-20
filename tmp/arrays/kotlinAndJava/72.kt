//File D.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class D {
   private final int x;

   public final boolean equals(@Nullable Object other, @NotNull String another) {
      return false;
   }

   public final int getX() {
      return this.x;
   }

   public D(int x) {
      this.x = x;
   }

   public final int component1() {
      return this.x;
   }

   @NotNull
   public final D copy(int x) {
      return new D(x);
   }

   // $FF: synthetic method
   public static D copy$default(D var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.x;
      }

      return var0.copy(var1);
   }

   @NotNull
   public String toString() {
      return "D(x=" + this.x + ")";
   }

   public int hashCode() {
      return Integer.hashCode(this.x);
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof D) {
            D var2 = (D)var1;
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

   public final boolean equals(@NotNull B other) {
      return false;
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


//File E.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class E {
   private final int x;

   public final boolean equals(@NotNull E x) {
      return false;
   }

   public boolean equals(@Nullable Object x) {
      return false;
   }

   public final int getX() {
      return this.x;
   }

   public E(int x) {
      this.x = x;
   }

   public final int component1() {
      return this.x;
   }

   @NotNull
   public final E copy(int x) {
      return new E(x);
   }

   // $FF: synthetic method
   public static E copy$default(E var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.x;
      }

      return var0.copy(var1);
   }

   @NotNull
   public String toString() {
      return "E(x=" + this.x + ")";
   }

   public int hashCode() {
      return Integer.hashCode(this.x);
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

fun box(): String {
  B::class.java.getDeclaredMethod("equals", Any::class.java)
  B::class.java.getDeclaredMethod("equals", B::class.java)

  C::class.java.getDeclaredMethod("equals", Any::class.java)
  C::class.java.getDeclaredMethod("equals")

  D::class.java.getDeclaredMethod("equals", Any::class.java)
  D::class.java.getDeclaredMethod("equals", Any::class.java, String::class.java)

  E::class.java.getDeclaredMethod("equals", Any::class.java)
  E::class.java.getDeclaredMethod("equals", E::class.java)

  return "OK"
}



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class C {
   private final int x;

   public final boolean equals() {
      return false;
   }

   public final int getX() {
      return this.x;
   }

   public C(int x) {
      this.x = x;
   }

   public final int component1() {
      return this.x;
   }

   @NotNull
   public final C copy(int x) {
      return new C(x);
   }

   // $FF: synthetic method
   public static C copy$default(C var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.x;
      }

      return var0.copy(var1);
   }

   @NotNull
   public String toString() {
      return "C(x=" + this.x + ")";
   }

   public int hashCode() {
      return Integer.hashCode(this.x);
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof C) {
            C var2 = (C)var1;
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
