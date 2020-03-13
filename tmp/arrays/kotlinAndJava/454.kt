//File D.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class D implements B {
   @NotNull
   public String foo() {
      return B.DefaultImpls.foo(this);
   }
}


//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   Object foo();
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface B {
   @NotNull
   String foo();

   public static final class DefaultImpls {
      @NotNull
      public static String foo(B $this) {
         return "A";
      }
   }
}


//File Main.kt


fun box(): String {
    val a: A = C()
    if (a.foo() != "A") return "Fail 1"
    if ((a as B).foo() != "A") return "Fail 2"
    if ((a as C).foo() != "A") return "Fail 3"
    return "OK"
}



//File C.java
import kotlin.Metadata;

public class C extends D implements A {
}
