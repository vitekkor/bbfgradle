//File D.java
import kotlin.Metadata;

public final class D {
   private int foo = 1;

   public final int getFoo() {
      return this.foo;
   }

   public final void foo() {
      this.foo = 2;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// IGNORE_BACKEND: JS_IR
// TODO: muted automatically, investigate should it be ran for JS or not
// IGNORE_BACKEND: JS

fun box(): String {
   D().foo()
   return "OK"
}

