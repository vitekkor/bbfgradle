//File A.java
import kotlin.Metadata;

public interface A {
   int getV();
}


//File AImpl.java
import kotlin.Metadata;

public final class AImpl implements A {
   private final int v = 5;

   public int getV() {
      return this.v;
   }
}


//File Main.kt


public fun box() : String {
    val a: A = AImpl()
    a.v
    return "OK"
}

