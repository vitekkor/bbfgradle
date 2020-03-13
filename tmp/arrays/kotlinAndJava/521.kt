//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   public final void test(@NotNull String $this$test, @NotNull String OK) {
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
    val clazz = A::class.java
    val method = clazz.getDeclaredMethod("test", String::class.java, String::class.java)
    val parameters = method.getParameters()

    if (!parameters[0].isImplicit() || parameters[0].isSynthetic()) return "wrong modifier on receiver parameter: ${parameters[0].modifiers}"

    return parameters[1].name
}

