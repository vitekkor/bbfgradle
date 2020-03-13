//File A.java
import kotlin.Metadata;

public interface A {
   int f();

   public static final class DefaultImpls {
      public static int f(A $this) {
         return 239;
      }
   }
}


//File B.java
import kotlin.Metadata;

public final class B implements A {
   public int f() {
      return A.DefaultImpls.f(this);
   }
}


//File Main.kt


fun box() : String {
    return if (B().f() == 239) "OK" else "fail"
}

