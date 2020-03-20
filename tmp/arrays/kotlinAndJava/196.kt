//File Test1.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class Test1 {
   @NotNull
   public final String test1() {
      return this instanceof Test2 ? ((Test2)this).foo() : "fail";
   }
}


//File Test2.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Test2 extends Test1 {
   @NotNull
   public final String foo() {
      return "OK";
   }
}


//File Main.kt


fun Test1.test2(): String {
    if (this is Test2) return this.foo() else return "fail"
}

fun box(): String {
    if ("OK" == Test2().test1() && "OK" == Test2().test2()) {
        return "OK"
    }
    return "fail"
}

