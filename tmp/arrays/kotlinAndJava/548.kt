//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   String foo();
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class B implements A {
   @NotNull
   public String foo() {
      return "OK";
   }
}


//File Main.kt


fun box() = (A::foo)(B())

