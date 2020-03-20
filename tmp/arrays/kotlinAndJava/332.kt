//File D.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface D {
   @NotNull
   Number foo();
}


//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   public Object foo() {
      return "A";
   }
}


//File E.java
import kotlin.Metadata;

public final class E extends C implements D {
}


//File Main.kt


fun box(): String {
    val e = E()
    if (e.foo() != 222) return "Fail 1"
    val d: D = e
    val c: C = e
    val a: A = e
    if (d.foo() != 222) return "Fail 2"
    if (c.foo() != 222) return "Fail 3"
    if (a.foo() != 222) return "Fail 4"
    return "OK"
}



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class C extends A {
   @NotNull
   public Integer foo() {
      return 222;
   }
}
