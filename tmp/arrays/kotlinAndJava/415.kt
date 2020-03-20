//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   public final String getY() {
      return "K";
   }
}


//File Main.kt
val x get() = "O"

fun box() = x + A().y

