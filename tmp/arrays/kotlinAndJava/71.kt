//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   public final String foo(@NotNull A p00, @NotNull A p01, @NotNull A p02, @NotNull A p03, @NotNull A p04, @NotNull A p05, @NotNull A p06, @NotNull A p07, @NotNull A p08, @NotNull A p09, @NotNull A p10, @NotNull A p11, @NotNull A p12, @NotNull A p13, @NotNull A p14, @NotNull A p15, @NotNull A p16, @NotNull A p17, @NotNull A p18, @NotNull A p19, @NotNull A p20, @NotNull A p21, @NotNull A p22, @NotNull A p23, @NotNull A p24, @NotNull A p25, @NotNull A p26, @NotNull A p27, @NotNull A p28, @NotNull A p29) {
      return "OK";
   }
}


//File Main.kt
// !LANGUAGE: +FunctionTypesWithBigArity
// IGNORE_BACKEND_FIR: JVM_IR

fun box(): String {
    val a = A()
    val ref = a::foo
    return ref(a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a)
}

