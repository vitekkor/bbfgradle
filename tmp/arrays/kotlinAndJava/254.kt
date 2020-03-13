//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   private final String s;

   @NotNull
   public final String getS() {
      return this.s;
   }

   public A(@NotNull String s) {
      super();
      this.s = s;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// SKIP_JDK6
// TARGET_BACKEND: JVM
// WITH_RUNTIME
// FULL_JDK
// KOTLIN_CONFIGURATION_FLAGS: +JVM.PARAMETERS_METADATA

fun test(OK: String) = object : A(OK) {
}

fun box(): String {
    val value = test("OK")
    val clazz = value.javaClass
    val constructor = clazz.getDeclaredConstructors().single()
    val parameters = constructor.getParameters()

    if (!parameters[0].isSynthetic()  || parameters[0].isImplicit()) return "wrong modifier on value parameter: ${parameters[0].modifiers}"
    return value.s
}

