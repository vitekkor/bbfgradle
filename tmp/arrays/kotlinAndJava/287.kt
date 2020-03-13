//File Base.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public abstract class Base {
   public final class Inner {
      @NotNull
      public final String o() {
         return "O";
      }

      @NotNull
      public final String k() {
         return "K";
      }
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
//KT-3927 Inner class cannot be instantiated with child instance of outer class

fun box(): String {
    var result = ""
    result += Child().Inner().o()

    fun Child.f() {
        result += Inner().k()
    }
    Child().f()

    return result
}



//File Child.java
import kotlin.Metadata;

public final class Child extends Base {
}
