//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class B implements ATrait {
   @NotNull
   public String foo2() {
      return ATrait.DefaultImpls.foo2(this);
   }
}


//File ATrait.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface ATrait {
   @NotNull
   String foo2();

   public static final class DefaultImpls {
      @NotNull
      public static String foo2(ATrait $this) {
         return "OK";
      }
   }
}


//File Main.kt


fun box() : String {
    val obj = C().D();
    return obj.foo
}



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class C extends B {
   public final class D {
      @NotNull
      private final String foo = C.super.foo2();

      @NotNull
      public final String getFoo() {
         return this.foo;
      }
   }
}
