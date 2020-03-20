//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   String getName();
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class B {
   @NotNull
   public final String getName() {
      return "OK";
   }
}


//File Main.kt


fun box(): String {
    return C().name
}



//File C.java
import kotlin.Metadata;

public final class C extends B implements A {
}
