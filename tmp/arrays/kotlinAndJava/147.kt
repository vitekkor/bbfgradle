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


fun localExtensionOnNullableParameter(): T {

    fun T.local(s: Int) {
        value += s
    }

    var t: T? = T(1)
    t?.local(2)

    return t!!
}


fun box(): String {
    val result = localExtensionOnNullableParameter().value
    if (result != 3) return "fail 2: $result"

    return "OK"
}

