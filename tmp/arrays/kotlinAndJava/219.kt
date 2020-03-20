//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   public final Object getX() {
      return new A.Inner() {
         @NotNull
         public String toString() {
            return A.this.foo();
         }
      };
   }

   @NotNull
   public final String foo() {
      return "OK";
   }

   public class Inner {
   }
}


//File Main.kt


fun box(): String = A().x.toString()

