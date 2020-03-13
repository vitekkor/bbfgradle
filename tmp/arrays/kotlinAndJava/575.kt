//File Base.java
import kotlin.Metadata;

public final class Base {
   private int count;

   public final int getCount() {
      int var1;
      this.count = (var1 = this.count) + 1;
      return var1;
   }

   public final void setCount(int var1) {
      this.count = var1;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
fun box(): String {
    var a = Base()

    val count = a.count
    if (count != 0) return "fail 1: $count"

    val count2 = a.count
    if (count2 != 1) return "fail 2: $count2"

    return "OK"

}

