//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   private final B b;

   @NotNull
   public final A a() {
      return (A)(new A(this.b) {
      });
   }

   @NotNull
   public final B getB() {
      return this.b;
   }

   public A(@NotNull B b) {
      super();
      this.b = b;
   }
}


//File B.java
import kotlin.Metadata;

public final class B {
}


//File Main.kt


fun box() : String {
    val b = B()
    val a = A(b).a()

    if (a.b !== b) return "failed"

    return "OK"
}

