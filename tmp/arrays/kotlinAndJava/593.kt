//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   String bar();

   public static final class DefaultImpls {
      private static String foo(A $this) {
         return "OK";
      }

      @NotNull
      public static String bar(A $this) {
         return foo($this);
      }
   }
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class B implements A {
   private final String foo() {
      return "fail";
   }

   @NotNull
   public String bar() {
      return A.DefaultImpls.bar(this);
   }
}


//File Main.kt
// IGNORE_BACKEND: JS

fun box() = B().bar()

