//File D.java
import kotlin.Metadata;

public final class D extends C {
}


//File Main.kt


fun box(): String {
    D().test(10)
    return "OK"
}



//File C.java
import kotlin.Metadata;

public abstract class C {
   public final void test(int x) {
      if (x != 0) {
         if (this instanceof D) {
            D d = (D)this;
            d.test(x - 1);
         }

      }
   }
}
