//File Foo.java
import kotlin.Metadata;

public final class Foo {
   public final boolean isOk() {
      return true;
   }
}


//File Main.kt


fun box(): String {
   val foo: Foo? = Foo()
   if (foo?.isOk()!!) return "OK"
   return "fail"
}

