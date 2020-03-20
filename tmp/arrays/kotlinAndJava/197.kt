//File Base.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class Base {
   public Base(@NotNull N n) {
      super();
   }
}


//File N.java
import kotlin.Metadata;

public interface N {
}


//File Main.kt


fun box() : String {
    Derived()
    return "OK"
}



//File Derived.java
import kotlin.Metadata;

public final class Derived extends Base {
   public Derived() {
      super((N)(new N() {
      }));
   }
}
