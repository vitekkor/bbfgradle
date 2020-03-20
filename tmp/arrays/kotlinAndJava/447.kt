//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   private final StringBuilder sb = new StringBuilder();

   public final void unaryPlus(@NotNull String $this$unaryPlus) {
      this.sb.append($this$unaryPlus);
   }

   @NotNull
   public final String foo() {
      this.unaryPlus("OK");
      String var10000 = this.sb.toString();
      if (var10000 == null) {
         }

      return var10000;
   }
}


//File Main.kt


fun box(): String = A().foo()

