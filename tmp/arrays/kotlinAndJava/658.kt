//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   public String foo() {
      return "FAIL";
   }

   @NotNull
   public final String bar() {
      return this instanceof C ? this.foo() : this.foo();
   }
}


//File B.java
import kotlin.Metadata;

public class B extends A {
}


//File Main.kt


fun box() = C().bar()



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class C extends B {
   @NotNull
   public String foo() {
      return "OK";
   }
}
