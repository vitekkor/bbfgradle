//File My.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class My {
   @NotNull
   private final String my = "O";

   @NotNull
   public final String getMy() {
      return this.my + "K";
   }
}


//File Main.kt


fun box() = My().my

