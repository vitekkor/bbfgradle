//File Main.kt


fun box(): String {
    val ss = SS()
    ss.setS("OK")
    return ss.getS()
}



//File SS.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class SS {
   private String s;

   public final void setS(@NotNull String s) {
      this.s = s;
   }

   @NotNull
   public final String getS() {
      String var10000 = this.s;
      if (var10000 == null) {
         }

      return var10000;
   }
}
