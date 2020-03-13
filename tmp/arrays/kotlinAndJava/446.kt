//File Outer.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Outer {
   public static final class Nested {
      @NotNull
      public final String box() {
         return "OK";
      }
   }
}


//File Main.kt


fun box() = Outer.Nested().box()

