//File Test.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Test {
   @NotNull
   private final String ok;

   @NotNull
   public final String getOk() {
      return this.ok;
   }

   public Test(@NotNull String ok) {
      super();
      this.ok = ok;
   }
}


//File Main.kt


fun box() = Test("OK").ok

