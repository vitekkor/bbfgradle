//File WaitFor.java
import kotlin.Metadata;

public abstract class WaitFor {
   public abstract boolean condition();

   public WaitFor() {
      this.condition();
   }
}


//File Main.kt


fun box(): String {
    val local = ""
    var result = "fail"
    val s = object: WaitFor() {

        override fun condition(): Boolean {
            result = "OK"
            return result.length== 2
        }
    }

    return result;
}

