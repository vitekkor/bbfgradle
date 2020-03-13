//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   @NotNull
   private final Foo a;

   @NotNull
   public final Foo getA() {
      return this.a;
   }

   public A(@NotNull Foo a) {
      super();
      this.a = a;
   }

   @NotNull
   public final Foo component1() {
      return this.a;
   }

   @NotNull
   public final A copy(@NotNull Foo a) {
      return new A(a);
   }

   // $FF: synthetic method
   public static A copy$default(A var0, Foo var1, int var2, Object var3) {
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
      Foo var10000 = this.a;
      return var10000 != null ? var10000.hashCode() : 0;
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof A) {
            A var2 = (A)var1;
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


//File Foo.java
import kotlin.Metadata;

public final class Foo {
   private final Object a;

   public final Object getA() {
      return this.a;
   }

   public Foo(Object a) {
      this.a = a;
   }
}


//File Main.kt


fun box() : String {
    val f1 = Foo("a")
    val f2 = Foo("b")
    val a = A(f1)
    val b = a.copy(f2)
    if (b.a.a == "b") {
        return "OK"
    }
    return "fail"
}

