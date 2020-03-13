//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public abstract class A {
   @NotNull
   public abstract String foo();
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class B extends A {
   @NotNull
   public String foo() {
      return "OK";
   }
}


//File Main.kt


fun box(): String = (A::foo)(B())

