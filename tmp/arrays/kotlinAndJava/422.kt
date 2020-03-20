//File Test.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Test {
   @NotNull
   public final String check(@Nullable Object a) {
      if ((Test)this == a) {
         return "Fail 1";
      } else {
         return (Test)this == a ? "Fail 2" : "OK";
      }
   }
}


//File Main.kt


fun box(): String = Test().check("String")

