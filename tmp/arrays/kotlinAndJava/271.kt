//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   public final String f() {
      return this instanceof B ? ((B)this).getX() : "FAIL";
   }
}


//File B.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class B extends A {
   @NotNull
   private final String x;

   @NotNull
   public final String getX() {
      return this.x;
   }

   public B(@NotNull String x) {
      super();
      this.x = x;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR

fun box() = B("OK").f()

