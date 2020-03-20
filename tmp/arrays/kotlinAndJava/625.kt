//File I.java
import kotlin.Metadata;

public interface I {
}


//File Main.kt


fun box(): String {
    C().foo()
    return "OK"
}



//File C.java
import kotlin.Metadata;

public final class C implements I {
   public final int foo() {
      return super.hashCode();
   }
}
