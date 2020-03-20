//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class A implements T1, T2 {
   @NotNull
   public String foo() {
      return T1.DefaultImpls.foo(this) + T2.DefaultImpls.foo(this);
   }
}


//File T1.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface T1 {
   @NotNull
   String foo();

   public static final class DefaultImpls {
      @NotNull
      public static String foo(T1 $this) {
         return "O";
      }
   }
}


//File T2.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface T2 {
   @NotNull
   String foo();

   public static final class DefaultImpls {
      @NotNull
      public static String foo(T2 $this) {
         return "K";
      }
   }
}


//File Main.kt


fun box() = A().foo()

