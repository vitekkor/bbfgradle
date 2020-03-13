//File Main.kt


fun box() : String {
    Greeter("OK").greet()
    return "OK"
}



//File Greeter.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Greeter {
   @NotNull
   private String name;

   public final void greet() {
      String var10001 = this.name;
      this.name = var10001 + "";
   }

   @NotNull
   public final String getName() {
      return this.name;
   }

   public final void setName(@NotNull String var1) {
      this.name = var1;
   }

   public Greeter(@NotNull String name) {
      super();
      this.name = name;
   }
}
