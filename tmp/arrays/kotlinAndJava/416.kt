//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class A {
   private final int x;

   @NotNull
   public final A plus(@NotNull A other) {
      return new A(this.x + other.x);
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


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
var log = ""

fun foo(): Int {
    log += "foo;"
    return 1
}
fun bar(): Int {
    log += "bar;"
    return 42
}

fun box(): String {
    val array = arrayOf(0, 1)
    array[foo()] += bar()

    if (array[0] != 0) return "fail1a: ${array[0]}"
    if (array[1] != 43) return "fail1b: ${array[0]}"

    log += "!;"

    val objArray = arrayOf(A(0), A(1))
    objArray[foo()] += A(bar())
    if (objArray[0] != A(0)) return "fail2a: ${array[0]}"
    if (objArray[1] != A(43)) return "fail2b: ${array[0]}"

    if (log != "foo;bar;!;foo;bar;") return "fail3: $log"

    return "OK"
}

