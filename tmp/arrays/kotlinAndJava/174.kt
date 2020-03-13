//File Main.kt


typealias Alias = C

fun box(): String {
    val c = Alias(23)
    if (c.x != "23") return "fail: $c"
    return "OK"
}



//File C.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class C {
   @NotNull
   private final String x;

   @NotNull
   public final String getX() {
      return this.x;
   }

   public C(@NotNull String x) {
      super();
      this.x = x;
   }

   public C(int n) {
      this(String.valueOf(n));
   }
}
