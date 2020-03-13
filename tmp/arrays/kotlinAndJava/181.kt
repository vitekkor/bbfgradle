//File My.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class My {
   @NotNull
   private String x;

   @NotNull
   public final String getX() {
      String var10000 = this.x;
      if (var10000 == null) {
         }

      return var10000;
   }

   public final void init() {
      this.x = "OK";
   }
}


//File Main.kt


fun box(): String {
    val my = My()
    my.init()
    return my.x
}

