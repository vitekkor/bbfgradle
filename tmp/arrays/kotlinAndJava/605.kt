//File Outer.java
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

public abstract class Outer {
   @Nullable
   public final Outer.Inner foo() {
      return null;
   }

   public final class Inner {
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// SKIP_JDK6
// TARGET_BACKEND: JVM
// WITH_REFLECT

fun box(): String {
    kotlin.test.assertEquals(
            "class Outer\$Inner",
            Outer::class.java.declaredMethods.single().genericReturnType.toString())

    return "OK"
}

