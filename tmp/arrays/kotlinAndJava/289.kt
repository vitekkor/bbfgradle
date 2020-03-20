//File Outer.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Outer {
   @NotNull
   private final String x = "O";

   @NotNull
   public final String getX() {
      return this.x;
   }

   public final class Inner {
      @NotNull
      private final String y = Outer.this.getX() + "K";

      @NotNull
      public final String getY() {
         return this.y;
      }
   }
}


//File Main.kt


fun box() = Outer().Inner().y

