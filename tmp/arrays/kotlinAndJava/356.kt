//File Foo.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Foo {
   @NotNull
   private final String s;

   @NotNull
   public final String getS() {
      return this.s;
   }

   public Foo(@NotNull String s) {
      super();
      this.s = s;
   }

   @NotNull
   public final String component1() {
      return this.s;
   }

   @NotNull
   public final Foo copy(@NotNull String s) {
      return new Foo(s);
   }

   // $FF: synthetic method
   public static Foo copy$default(Foo var0, String var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.s;
      }

      return var0.copy(var1);
   }

   @NotNull
   public String toString() {
      return "Foo(s=" + this.s + ")";
   }

   public int hashCode() {
      String var10000 = this.s;
      return var10000 != null ? var10000.hashCode() : 0;
   }

   public boolean equals(@Nullable Object var1) {
      if (this != var1) {
         if (var1 instanceof Foo) {
            Foo var2 = (Foo)var1;
            if (Intrinsics.areEqual(this.s, var2.s)) {
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
// !LANGUAGE: -DataClassInheritance
// IGNORE_BACKEND: JS_IR
// IGNORE_BACKEND: JVM_IR

fun box(): String {
    val f1 = Foo("OK")
    val f2 = Foo("OK")
    if (f1 != f2) return "Fail equals"
    if (f1.hashCode() != f2.hashCode()) return "Fail hashCode"
    if (f1.toString() != f2.toString() || f1.toString() != "Foo(s=OK)") return "Fail toString: $f1 $f2"

    return f1.s
}

