//File A.java
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

public class A {
   @JvmField
   @NotNull
   public final String publicField = "1";
   @JvmField
   @NotNull
   public final String internalField = "2";
   @JvmField
   @NotNull
   protected final String protectedField = "34";

   @NotNull
   public final String test() {
      return (String)((Function0)(new Function0() {
         @NotNull
         public final String invoke() {
            return A.this.publicField + A.this.internalField + A.this.protectedField;
         }
      })).invoke();
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME


fun box(): String {
    return if (A().test() == "1234") return "OK" else "fail"
}

