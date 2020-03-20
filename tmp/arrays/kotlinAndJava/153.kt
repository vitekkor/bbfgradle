//File T.java
import kotlin.Metadata;

public class T {
   private int value;

   public final int getValue() {
      return this.value;
   }

   public final void setValue(int var1) {
      this.value = var1;
   }

   public T(int value) {
      this.value = value;
   }
}


//File Main.kt


fun plusAssign(): T {

    operator fun T.plusAssign(s: Int) {
        value += s
    }

    var t  = T(1)
    t += 1

    return t
}

fun box(): String {
    val result = plusAssign().value
    if (result != 2) return "fail 1: $result"

    return "OK"
}

