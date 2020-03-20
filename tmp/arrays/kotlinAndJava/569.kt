//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   public final String foo(@NotNull String result) {
      return result;
   }
}


//File Main.kt


fun box(): String {
    val x = A::foo
    return x(A(), "OK")
}

