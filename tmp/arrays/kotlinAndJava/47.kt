//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   public String foo() {
      return "OK";
   }
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class B extends A {
   @NotNull
   public String foo() {
      return super.foo();
   }
}


//File I.java
import kotlin.Metadata;

public interface I {
}


//File Main.kt


fun box() = C().foo()



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class C extends B implements I {
   @NotNull
   public String foo() {
      return super.foo();
   }
}
