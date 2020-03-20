//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   String getFoo();

   public static final class DefaultImpls {
      @NotNull
      public static String getFoo(A $this) {
         return "OK";
      }
   }
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class B implements A {
   @NotNull
   public String getFoo() {
      return A.DefaultImpls.getFoo(this);
   }
}


//File Main.kt


fun box() = C().D().foo



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class C extends B {
   public final class D {
      @NotNull
      private final String foo = C.super.getFoo();

      @NotNull
      public final String getFoo() {
         return this.foo;
      }
   }
}
