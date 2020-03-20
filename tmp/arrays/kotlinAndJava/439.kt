//File D.java
import kotlin.Metadata;

public final class D extends Right implements Left {
   public int f() {
      return 239;
   }
}


//File Right.java
import kotlin.Metadata;

public class Right {
   public int f() {
      return 42;
   }
}


//File Left.java
import kotlin.Metadata;

public interface Left {
}


//File Main.kt
// Changed when traits were introduced. May not make sense any more

fun box() : String {
    val r : Right = Right()
    val d : D = D()

    if (r.f() != 42) return "Fail #1"
    if (d.f() != 239) return "Fail #2"

    return "OK"
}

