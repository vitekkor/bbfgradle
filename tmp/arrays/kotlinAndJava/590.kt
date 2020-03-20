//File Bar.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Bar extends Foo {
   @NotNull
   public final String test() {
      return this.xyzzy();
   }
}


//File Foo.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class Foo {
   @NotNull
   public final String xyzzy() {
      return "xyzzy";
   }
}


//File Main.kt


fun box() : String {
  val bar = Bar()
  val f = bar.test()
  return if (f == "xyzzy") "OK" else "fail"
}

