//File Test.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Test {
   public static final class Nested {
      @NotNull
      private final String value = "OK";

      @NotNull
      public final String getValue() {
         return this.value;
      }
   }
}


//File Main.kt


fun Test.Nested.foo() = value

fun box() = Test.Nested().foo()

