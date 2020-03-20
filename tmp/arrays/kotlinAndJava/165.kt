//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public abstract class A {
   public final class InnerInA {
      @NotNull
      public final String returnOk() {
         return "OK";
      }
   }
}


//File B.java
import kotlin.Metadata;

public final class B extends A {
}


//File Main.kt


fun foo(a: A): String {
    if (a is B) {
        val v = a::InnerInA
        return v().returnOk()
    }

    return "error"
}

fun box(): String {
    return foo(B())
}

