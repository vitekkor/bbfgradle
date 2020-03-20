//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class A {
   public void f(@NotNull Object[] args) {
      }
}


//File B.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class B extends A {
   public void f(@NotNull String[] args) {
      }
}


//File Main.kt


fun box(): String {
    B()
    return "OK"
}

