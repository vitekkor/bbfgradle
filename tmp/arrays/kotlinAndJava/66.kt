//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   private String result = "Fail";

   @NotNull
   public final String getResult() {
      return this.result;
   }

   public final void setResult(@NotNull String var1) {
      this.result = var1;
   }
}


//File Main.kt
fun run(arg1: A, arg2: String, funRef:A.(String) -> Unit): Unit {
    return arg1.funRef(arg2)
}

fun A.foo(newResult: String) {
    result = newResult
}

fun box(): String {
    val a = A()
    val x = A::foo
    x(a, "OK")

    if (a.result != "OK") return a.result

    val a1 = A()
    run(a1, "OK", A::foo)
    if (a1.result != "OK") return a1.result

    return "OK"
}

