//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   private final String foo = "OK";

   @NotNull
   public String getFoo() {
      return this.foo;
   }
}


//File B.java
import kotlin.Metadata;

public class B extends A {
}


//File Main.kt


fun box() = C().D().foo



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class C extends B {
   public final class D {
      @NotNull
      private final String foo = C.super.getFoo();

      @NotNull
      public final String getFoo() {
         return this.foo;
      }
   }
}
