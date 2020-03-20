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



fun box() : String {
    var s = "1"
    s += "2" + 3 + 4L + 5.0 + 6F + '7' + A()

    return "OK"
}

// 1 NEW java/lang/StringBuilder

