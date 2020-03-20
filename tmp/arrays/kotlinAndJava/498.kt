//File Request.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Request {
   @NotNull
   private final String path;

   @NotNull
   public final String getPath() {
      return this.path;
   }

   public Request(@NotNull String path) {
      super();
      this.path = path;
   }
}


//File Main.kt


fun box() : String = if(Handler().test(Request("239")) == "239") "OK" else "fail"



//File Handler.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Handler {
   public final void times(int $this$times, @NotNull Function0 op) {
      int i = 0;
      int var4 = $this$times;
      if (i <= $this$times) {
         while(true) {
            op.invoke();
            if (i == var4) {
               break;
            }

            ++i;
         }
      }

   }

   @NotNull
   public final String getPath(@NotNull Request $this$getPath) {
      return $this$getPath.getPath();
   }

   @NotNull
   public final String test(@NotNull Request request) {
      return this.getPath(request);
   }
}
