//File MyException.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class MyException extends Exception {
   public MyException(@NotNull String message) {
      super(message);
   }
}


//File Main.kt


fun box(): String =
        "O" +
        try {
            try { throw Exception("oops!") } catch (mye: MyException) { "1" }
        }
        catch (e: Exception) {
            "K"
        }

