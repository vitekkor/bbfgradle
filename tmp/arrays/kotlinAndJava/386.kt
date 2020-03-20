//File Outer.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Outer {
   @NotNull
   private final String result = "OK";

   @NotNull
   public final String getResult() {
      return this.result;
   }

   public final class Inner {
      @NotNull
      public final String foo() {
         return Outer.this.getResult();
      }
   }
}


//File Main.kt


fun box(): String {
    val f = Outer.Inner::foo
    return f(Outer().Inner())
}

