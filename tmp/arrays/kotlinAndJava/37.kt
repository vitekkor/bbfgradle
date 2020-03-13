//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   Object foo();
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class B {
   @NotNull
   public final String foo() {
      return "A";
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// KT-4145

fun box(): String {
    val a: A = C()
    if (a.foo() != "A") return "Fail 1"
    if ((a as B).foo() != "A") return "Fail 2"
    if ((a as C).foo() != "A") return "Fail 3"
    return "OK"
}



//File C.java
import kotlin.Metadata;

public class C extends B implements A {
}
