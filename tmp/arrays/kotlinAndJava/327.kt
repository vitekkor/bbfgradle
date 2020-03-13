//File Abstract.java
import kotlin.Metadata;

public interface Abstract {
}


//File Derived2.java
import kotlin.Metadata;

public final class Derived2 extends Base implements Abstract {
}


//File Derived1.java
import kotlin.Metadata;

public final class Derived1 extends Base implements Abstract {
}


//File Base.java
import kotlin.Metadata;

public class Base {
   public final int n(int n) {
      return n + 1;
   }
}


//File Main.kt


fun test(s : Base) : Boolean = s.n(238) == 239

fun box() : String {
    if (!test(Base())) return "Fail #1"
    if (!test(Derived1())) return "Fail #2"
    if (!test(Derived2())) return "Fail #3"
    return "OK"
}

