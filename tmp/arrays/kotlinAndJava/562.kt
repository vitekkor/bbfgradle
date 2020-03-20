//File Bar.java
import kotlin.Metadata;

public final class Bar extends Foo {
}


//File Foo.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public abstract class Foo {
   @NotNull
   public final String hello(Object id) {
      return "" + 'O' + id;
   }
}


//File Main.kt


fun box() = Bar().hello("K")

