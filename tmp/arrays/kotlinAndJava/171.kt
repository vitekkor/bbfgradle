//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   Object foo();

   public static final class DefaultImpls {
      @NotNull
      public static Object foo(A $this) {
         return "A";
      }
   }
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface B extends A {
   @NotNull
   String foo();

   public static final class DefaultImpls {
      @NotNull
      public static String foo(B $this) {
         return "B";
      }
   }
}


//File Main.kt


fun box(): String {
    val c = C()
    val b: B = c
    val a: A = c
    var r = c.foo() + b.foo() + a.foo()
    return if (r == "BBB") "OK" else "Fail: $r"
}



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class C implements B {
   @NotNull
   public String foo() {
      return B.DefaultImpls.foo(this);
   }
}
