//File My.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class My {
   private final String back = "O";

   @NotNull
   public final String getMy() {
      return (new Your() {
         @NotNull
         private final String your;

         @NotNull
         public String getYour() {
            return this.your;
         }

         {
            this.your = My.this.back;
         }
      }).foo() + "K";
   }
}


//File Your.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public abstract class Your {
   @NotNull
   public abstract String getYour();

   @NotNull
   public final String foo() {
      return this.getYour();
   }
}


//File Main.kt


fun box() = My().my

