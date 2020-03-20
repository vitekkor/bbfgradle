//File Foo.java
import kotlin.Metadata;

public final class Foo extends SuperFoo {
   public final void superFoo() {
   }
}


//File SuperFoo.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class SuperFoo {
   @NotNull
   public final String bar() {
      if (this instanceof Foo) {
         ((Foo)this).superFoo();
         return this.baz();
      } else {
         return this.baz();
      }
   }

   @NotNull
   public final String baz() {
      return "OK";
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR

fun box(): String = Foo().bar()

