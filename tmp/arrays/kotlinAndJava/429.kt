//File D.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class D {
   @Nullable
   private final Integer a;

   @Nullable
   public final Integer getA() {
      return this.a;
   }

   public D(@Nullable Integer a) {
      this.a = a;
   }

   @Nullable
   public final Integer component1() {
      return this.a;
   }

   @NotNull
   public final D copy(@Nullable Integer a) {
      return new D(a);
   }

   // $FF: synthetic method
   public static D copy$default(D var0, Integer var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.a;
      }

      return var0.copy(var1);
   }

   @NotNull
   public String toString() {
      return "D(a=" + this.a + ")";
   }

   public int hashCode() {
      Integer var10000 = this.a;
      return var10000 != null ? var10000.hashCode() : 0;
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof D) {
            D var2 = (D)var1;
            if (Intrinsics.areEqual(this.a, var2.a)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }
}


//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   @Nullable
   private final Object a;
   private int x;

   @Nullable
   public final Object getA() {
      return this.a;
   }

   public final int getX() {
      return this.x;
   }

   public final void setX(int var1) {
      this.x = var1;
   }

   public A(@Nullable Object a, int x) {
      this.a = a;
      this.x = x;
   }

   @Nullable
   public final Object component1() {
      return this.a;
   }

   public final int component2() {
      return this.x;
   }

   @NotNull
   public final A copy(@Nullable Object a, int x) {
      return new A(a, x);
   }

   // $FF: synthetic method
   public static A copy$default(A var0, Object var1, int var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.a;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.x;
      }

      return var0.copy(var1, var2);
   }

   @NotNull
   public String toString() {
      return "A(a=" + this.a + ", x=" + this.x + ")";
   }

   public int hashCode() {
      Object var10000 = this.a;
      return (var10000 != null ? var10000.hashCode() : 0) * 31 + Integer.hashCode(this.x);
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof A) {
            A var2 = (A)var1;
            if (Intrinsics.areEqual(this.a, var2.a) && this.x == var2.x) {
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
   @Nullable
   private final Object a;

   @Nullable
   public final Object getA() {
      return this.a;
   }

   public B(@Nullable Object a) {
      this.a = a;
   }

   @Nullable
   public final Object component1() {
      return this.a;
   }

   @NotNull
   public final B copy(@Nullable Object a) {
      return new B(a);
   }

   // $FF: synthetic method
   public static B copy$default(B var0, Object var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.a;
      }

      return var0.copy(var1);
   }

   @NotNull
   public String toString() {
      return "B(a=" + this.a + ")";
   }

   public int hashCode() {
      Object var10000 = this.a;
      return var10000 != null ? var10000.hashCode() : 0;
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof B) {
            B var2 = (B)var1;
            if (Intrinsics.areEqual(this.a, var2.a)) {
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
   if( A(null,19).hashCode() != 19) "fail"
   if( A(239,19).hashCode() != (239*31+19)) "fail"
   if( B(null).hashCode() != 0) "fail"
   if( B(239).hashCode() != 239) "fail"
   if( C(239,19).hashCode() != (239*31+19)) "fail"
   if( C(239,null).hashCode() != 239*31) "fail"
   if( D(239).hashCode() != (239)) "fail"
   if( D(null).hashCode() != 0) "fail"
   return "OK"
}



//File C.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class C {
   private final int a;
   @Nullable
   private Integer x;

   public final int getA() {
      return this.a;
   }

   @Nullable
   public final Integer getX() {
      return this.x;
   }

   public final void setX(@Nullable Integer var1) {
      this.x = var1;
   }

   public C(int a, @Nullable Integer x) {
      this.a = a;
      this.x = x;
   }

   public final int component1() {
      return this.a;
   }

   @Nullable
   public final Integer component2() {
      return this.x;
   }

   @NotNull
   public final C copy(int a, @Nullable Integer x) {
      return new C(a, x);
   }

   // $FF: synthetic method
   public static C copy$default(C var0, int var1, Integer var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.a;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.x;
      }

      return var0.copy(var1, var2);
   }

   @NotNull
   public String toString() {
      return "C(a=" + this.a + ", x=" + this.x + ")";
   }

   public int hashCode() {
      int var10000 = Integer.hashCode(this.a) * 31;
      Integer var10001 = this.x;
      return var10000 + (var10001 != null ? var10001.hashCode() : 0);
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof C) {
            C var2 = (C)var1;
            if (this.a == var2.a && Intrinsics.areEqual(this.x, var2.x)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }
}
