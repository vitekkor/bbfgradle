//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   Object getResult();

   public static final class DefaultImpls {
      @NotNull
      public static Object getResult(A $this) {
         return "Fail";
      }
   }
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface B extends A {
   @NotNull
   String getResult();

   public static final class DefaultImpls {
      @NotNull
      public static String getResult(B $this) {
         return "OK";
      }
   }
}


//File AImpl.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public abstract class AImpl implements A {
   @NotNull
   public Object getResult() {
      return A.DefaultImpls.getResult(this);
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR

fun box(): String =
    (BImpl() as A).result.toString()



//File BImpl.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class BImpl extends AImpl implements B {
   @NotNull
   public String getResult() {
      return B.DefaultImpls.getResult(this);
   }
}
