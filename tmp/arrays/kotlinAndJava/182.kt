//File A.java
import java.io.PrintStream;
import kotlin.Metadata;

public final class A {
   public final void foo() {
      PrintStream var10000 = System.out;
      if (var10000 != null) {
         var10000.println(1);
      }

   }
}


//File Main.kt
// IGNORE_BACKEND: JS_IR
// TODO: muted automatically, investigate should it be ran for JS or not
// IGNORE_BACKEND: JS, NATIVE

fun box() : String {
    val a : A = A()
    return "OK"
}

