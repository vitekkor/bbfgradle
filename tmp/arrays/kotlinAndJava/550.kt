//File B.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

public final class B implements Function0 {
   @NotNull
   public Boolean invoke() {
      return true;
   }

   public final boolean foo() {
      return this.invoke();
   }
}


//File Main.kt
// IGNORE_BACKEND: JS_IR
//KT-3822 Compiler crashes when use invoke convention with `this` in class which extends Function0<T>
// IGNORE_BACKEND: JS
// JS backend does not allow to implement Function{N} interfaces

fun box() = if (B().foo()) "OK" else "fail"

