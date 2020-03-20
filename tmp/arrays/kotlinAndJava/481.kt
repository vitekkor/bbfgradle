//File Test.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public enum Test {
   OK;

   @NotNull
   private final int[] values;

   @NotNull
   public final int[] getValues() {
      return this.values;
   }

   private Test(int... xs) {
      this.values = xs;
   }
}


//File Main.kt


fun box(): String =
        if (Test.OK.values.size == 0) "OK" else "Fail"

