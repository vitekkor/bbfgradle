//File KeySpan.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class KeySpan {
   @NotNull
   private final String left;

   public final boolean matches(@NotNull String value) {
      return this.left.compareTo(value) > 0 && this.left.compareTo(value) > 0;
   }

   @NotNull
   public final String getLeft() {
      return this.left;
   }

   public KeySpan(@NotNull String left) {
      super();
      this.left = left;
   }
}


//File Main.kt


fun box() : String {
  KeySpan("1").matches("3")
  return "OK"
}

