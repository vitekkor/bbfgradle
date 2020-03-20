//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   String foo();

   public static final class DefaultImpls {
      private static String getLog(A $this) {
         return "O";
      }

      private static String bar(A $this) {
         return "K";
      }

      @NotNull
      public static String foo(A $this) {
         return getLog($this) + bar($this);
      }
   }
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface B extends A {
   public static final class DefaultImpls {
      @NotNull
      public static String foo(B $this) {
         return A.DefaultImpls.foo((A)$this);
      }
   }
}


//File Main.kt


fun box() = C().foo()



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class C implements B {
   @NotNull
   public String foo() {
      return B.DefaultImpls.foo(this);
   }
}
