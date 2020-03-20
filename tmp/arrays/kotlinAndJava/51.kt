//File Main.kt


fun box(): String {
    var t = CustomException("O", Throwable("K"))
    if (t.message != "O" || t.cause?.message != "K") return "fail1"

    t = CustomException(Throwable("OK"))
    if (t.message == null || t.message == "OK" || t.cause?.message != "OK") return "fail2"

    t = CustomException("OK")
    if (t.message != "OK" || t.cause != null) return "fail3"

    t = CustomException()
    if (t.message != null || t.cause != null) return "fail4"

    return "OK"
}



//File CustomException.java
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

public final class CustomException extends Throwable {
   public CustomException(@Nullable String message, @Nullable Throwable cause) {
      super(message, cause);
   }

   public CustomException(@Nullable String message) {
      super(message, (Throwable)null);
   }

   public CustomException(@Nullable Throwable cause) {
      super(cause);
   }

   public CustomException() {
   }
}
