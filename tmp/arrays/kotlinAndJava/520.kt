//File A.java
import kotlin.Metadata;

public final class A {
   public final int invoke() {
      return 42;
   }

   public final int foo() {
      return this.invoke();
   }
}


//File Main.kt
//KT-3821 Invoke convention doesn't work for `this`

fun box() = if (A().foo() == 42) "OK" else "fail"

