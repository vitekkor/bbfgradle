//File Test.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Test {
   @NotNull
   private final String a = "1";

   @NotNull
   public final String getA() {
      return this.a;
   }

   private final String getB() {
      return this.a;
   }

   public final int outer() {
      return this.getB().length();
   }
}


//File Main.kt


fun box() = if (Test().outer() == 1) "OK" else "fail"

