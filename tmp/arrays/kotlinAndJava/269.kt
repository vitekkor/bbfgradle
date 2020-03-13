//File My.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

public final class My {
   @NotNull
   private final String my = "O";

   @NotNull
   public final String getMy() {
      return (String)((Function0)(new Function0() {
         @NotNull
         public final String invoke() {
            return My.this.my;
         }
      })).invoke() + "K";
   }
}


//File Main.kt


fun box() = My().my

