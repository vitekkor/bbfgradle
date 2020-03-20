//File My.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class My {
   @NotNull
   private String my = "U";

   @NotNull
   public final String getMy() {
      return (String)((Function0)(new Function0() {
         @NotNull
         public final String invoke() {
            return My.this.my;
         }
      })).invoke();
   }

   public final void setMy(@NotNull final String arg) {

      final class Local {
         public final void foo() {
            My.this.my = arg + "K";
         }

         public Local() {
         }
      }

      (new Local()).foo();
   }
}


//File Main.kt


fun box(): String {
    val m = My()
    m.my = "O"
    return m.my
}

