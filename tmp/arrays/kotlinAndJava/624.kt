//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   private final String x;

   @NotNull
   public final A f() {
      int $i$f$f = 0;
      return this instanceof C ? this : new A("O");
   }

   @NotNull
   public final A getY() {
      int $i$f$getY = 0;
      return this instanceof C ? this : new A("K");
   }

   @NotNull
   public final String getX() {
      return this.x;
   }

   public A(@NotNull String x) {
      super();
      this.x = x;
   }
}


//File B.java
import kotlin.Metadata;

public final class B extends A {
   public B() {
      super("unused");
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM
// JVM_TARGET: 1.8

// If the receiver is not CHECKCASTed to A when inlining, asm will infer Object
// for the result of `if` in `f` instead of A when generating stack maps because
// one branch has type A while the other has type B (a subtype of A, but asm
// does not know that). This would cause a JVM validation error.
fun box() = B().f().x + B().y.x



//File C.java
import kotlin.Metadata;

public final class C extends A {
   public C() {
      super("unused");
   }
}
