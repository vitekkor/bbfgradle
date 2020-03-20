//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   private final String value;

   @NotNull
   public final String getValue() {
      return this.value + "K";
   }

   public A(@NotNull String o) {
      super();
      this.value = o;
   }
}


//File Main.kt


fun box() = A("O").value

