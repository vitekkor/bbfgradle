//File Main.kt


fun box(): String {
    var a : Shape? = Shape("fail");
    a?.result = "OK";

    return a!!.result
}



//File Shape.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Shape {
   @NotNull
   private String result;

   @NotNull
   public final String getResult() {
      return this.result;
   }

   public final void setResult(@NotNull String var1) {
      this.result = var1;
   }

   public Shape(@NotNull String result) {
      super();
      this.result = result;
   }
}
