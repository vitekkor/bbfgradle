//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   String foo(@NotNull String var1);

   public static final class DefaultImpls {
      @NotNull
      public static String foo(A $this, @NotNull String o) {
         return o + "K";
      }
   }
}


//File B.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public interface B extends A {
   public static final class DefaultImpls {
      @NotNull
      public static String foo(B $this, @NotNull String o) {
         return A.DefaultImpls.foo((A)$this, o);
      }
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR

fun box(): String = C().foo("O")



//File C.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class C implements B {
   @NotNull
   public String foo(@NotNull String o) {
      return B.DefaultImpls.foo(this, o);
   }
}
