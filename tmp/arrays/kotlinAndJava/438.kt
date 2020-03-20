//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   public final String foo() {
      return this instanceof A.B ? ((A.B)this).getA() : "OK";
   }

   public static final class B extends A {
      @NotNull
      private final String a = "FAIL";

      @NotNull
      public final String getA() {
         return this.a;
      }
   }
}


//File Main.kt



fun box(): String = A().foo()

