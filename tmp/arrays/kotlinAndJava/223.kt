//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   private String x;

   @NotNull
   public final String getX() {
      String var10000 = this.x;
      if (var10000 == null) {
         }

      return var10000;
   }

   protected final void set(@NotNull String value) {
      this.x = value;
   }
}


//File B.java
import kotlin.Metadata;

public final class B extends A {
   public final void init() {
      this.set("OK");
   }
}


//File Main.kt


fun box(): String {
    val b = B()
    b.init()
    return b.x
}

