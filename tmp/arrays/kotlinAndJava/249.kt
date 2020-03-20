//File D.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface D extends B, C {
   public static final class DefaultImpls {
      @NotNull
      public static String foo(D $this) {
         return C.DefaultImpls.foo((C)$this);
      }
   }
}


//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   String foo();

   public static final class DefaultImpls {
      @NotNull
      public static String foo(A $this) {
         return "Fail";
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


//File Impl.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Impl implements D {
   @NotNull
   public String foo() {
      return D.DefaultImpls.foo(this);
   }
}


//File Main.kt


fun box(): String = Impl().foo()



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface C extends A {
   @NotNull
   String foo();

   public static final class DefaultImpls {
      @NotNull
      public static String foo(C $this) {
         return "OK";
      }
   }
}
