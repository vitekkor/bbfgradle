//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   public String toString() {
      return "A";
   }
}


//File Main.kt
// IGNORE_BACKEND: JS

fun box() : String {

    val s = "1" + "2" + 3 + 4L + 5.0 + 6F + '7' + A()

    if (s != "12345.06.07A") return "fail $s"

    return "OK"
}

