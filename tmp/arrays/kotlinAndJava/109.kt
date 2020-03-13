//File T.java
import kotlin.Metadata;

public final class T {
   private final int value;

   public final int getValue() {
      return this.value;
   }

   public T(int value) {
      this.value = value;
   }
}


//File Main.kt


fun local() : Int {

    operator fun T.get(s: Int): Int {
        return s * this.value
    }

    var t  = T(11)
    return t[2]
}

fun box() : String {
    if (local() != 22) return "fail1 ${local()} "

    return "OK"
}

