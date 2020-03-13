//File GameError.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class GameError extends Exception {
   public GameError(@NotNull String msg) {
      super(msg);
   }
}


//File Main.kt


fun box(): String {
  val e = GameError("foo")
  return if (e.message == "foo") "OK" else "fail"
}

