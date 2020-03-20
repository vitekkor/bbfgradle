//File Test.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Test {
   private final String getFoo(int $this$foo) {
      return "OK";
   }

   @NotNull
   public final String test() {
      return this.getFoo(1);
   }
}


//File Main.kt


fun box(): String {
    return Test().test()
}

