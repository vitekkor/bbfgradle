//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   public final String box() {
      final class Local extends A.Inner {
         private final int u = A.this.foo();

         public final int getU() {
            return this.u;
         }

         public Local() {
            super();
         }
      }

      int u = (new Local()).getU();
      return u == 42 ? "OK" : "Fail " + u;
   }

   public final int foo() {
      return 42;
   }

   public class Inner {
   }
}


//File Main.kt


fun box() = A().box()

