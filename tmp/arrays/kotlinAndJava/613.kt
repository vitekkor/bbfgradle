//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class A {
   public static final class Nested {
      @NotNull
      private final String result = "OK";

      @NotNull
      public final String getResult() {
         return this.result;
      }
   }
}


//File Main.kt


fun box() = (A::Nested)().result

