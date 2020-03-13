//File Test.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public interface Test {
   @NotNull
   String test(@NotNull String var1);

   public static final class DefaultImpls {
      @NotNull
      public static String test(Test $this, @NotNull String OK) {
         return "123";
      }
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// SKIP_JDK6
// TARGET_BACKEND: JVM
// WITH_RUNTIME
// FULL_JDK
// KOTLIN_CONFIGURATION_FLAGS: +JVM.PARAMETERS_METADATA


fun box(): String {
    val testMethod = Class.forName("Test\$DefaultImpls").declaredMethods.single()
    val parameters = testMethod.getParameters()

    if (!parameters[0].isSynthetic()) return "wrong modifier on receiver parameter: ${parameters[0].modifiers}"

    if (parameters[1].modifiers != 0) return "wrong modifier on value parameter: ${parameters[1].modifiers}"

    return parameters[1].name
}

