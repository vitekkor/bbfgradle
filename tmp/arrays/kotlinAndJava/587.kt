//File Bar.java
import kotlin.Metadata;

public final class Bar extends Foo {
   public final class Baz {
      public final void call() {
         Bar s = Bar.this;
         s.isOpen();
      }
   }
}


//File Foo.java
import kotlin.Metadata;

public abstract class Foo {
   private boolean isOpen = true;

   public final boolean isOpen() {
      return this.isOpen;
   }
}


//File Main.kt

fun box(): String {
    Bar().Baz()
    return "OK"
}

