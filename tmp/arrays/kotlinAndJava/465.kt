//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   Number foo();

   public static final class DefaultImpls {
      @NotNull
      public static Number foo(A $this) {
         return (Number)42;
      }
   }
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface B extends A {
   public static final class DefaultImpls {
      @NotNull
      public static Number foo(B $this) {
         return A.DefaultImpls.foo((A)$this);
      }
   }
}


//File Main.kt


fun box(): String {
    val x = C().foo()
    return if (x == 42) "OK" else "Fail: $x"
}



//File C.java
import kotlin.Metadata;
import kotlin.TypeCastException;
import org.jetbrains.annotations.NotNull;

public final class C implements B {
   @NotNull
   public Integer foo() {
      Number var10000 = B.DefaultImpls.foo(this);
      if (var10000 == null) {
         throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
      } else {
         return (Integer)var10000;
      }
   }
}
