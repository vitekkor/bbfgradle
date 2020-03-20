//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   public String foo2() {
      return "OK";
   }
}


//File B.java
import kotlin.Metadata;

public class B extends A {
}


//File Main.kt


fun box() : String {
    val obj = C().D();
    return obj.foo
}



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class C extends B {
   public final class D {
      @NotNull
      private final String foo = C.super.foo2();

      @NotNull
      public final String getFoo() {
         return this.foo;
      }
   }
}
