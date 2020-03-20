//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   public String method() {
      return "OK";
   }
}


//File B.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

public final class B extends A {
   @NotNull
   public String method() {
      return (String)((Function0)(new Function0() {
         @NotNull
         public final String invoke() {
            return B.super.method();
         }
      })).invoke();
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
fun box() =
    B().method()

