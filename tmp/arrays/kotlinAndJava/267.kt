//File Outer.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Outer {
   public final class Inner {
      @NotNull
      public final String box() {
         return "OK";
      }
   }
}


//File Main.kt


fun box() = Outer().Inner().box()

