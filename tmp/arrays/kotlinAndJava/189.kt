//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   public final String foo() {
      return "OK";
   }
}


//File Main.kt


fun A?.bar() = if (this != null) foo() else "FAIL"

fun box() = A().bar()

