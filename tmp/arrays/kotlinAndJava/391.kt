//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   private final String s;

   @NotNull
   public final String getS() {
      return this.s;
   }

   public A(@NotNull String s) {
      super();
      this.s = s;
   }
}


//File Main.kt


fun box(): String {
    class B {
        val result = "OK"

        val f = object : A(result) {}.s
    }

    return B().f
}

