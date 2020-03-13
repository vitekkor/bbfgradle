//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   public final String a() {
      final class B {
         @NotNull
         public final String s() {
            return "OK";
         }

         public B() {
         }
      }

      return (new B()).s();
   }
}


//File Main.kt


fun box() : String {
    return A().a()
}

