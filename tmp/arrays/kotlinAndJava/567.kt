//File A.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   public final Function0 f() {
      final String s = "OK";
      return (Function0)(new Function0() {
         @NotNull
         public final String invoke() {
            return s;
         }
      });
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

fun box(): String {
    val lambdaClass = A().f().javaClass
    val fields = lambdaClass.getDeclaredFields().toList()
    if (fields.size != 1) return "Fail: lambda should only capture 's': $fields"

    val fieldName = fields[0].getName()
    if (fieldName != "\$s") return "Fail: captured variable should be named '\$s': $fields"

    return "OK"
}

