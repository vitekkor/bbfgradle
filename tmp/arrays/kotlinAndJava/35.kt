//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   private String prop;

   @NotNull
   public final String getProp() {
      return this.prop;
   }

   public final void setProp(@NotNull String var1) {
      this.prop = var1;
   }

   public A() {
      this.prop = "";
      MainKt.setSideEffects(MainKt.getSideEffects() + this.prop + "first");
      MainKt.setSideEffects(MainKt.getSideEffects() + this.prop + "#second");
   }

   public A(@NotNull String x) {
      this();
      this.prop = x;
      MainKt.setSideEffects(MainKt.getSideEffects() + "#third");
   }

   public A(int x) {
      this(String.valueOf(x));
      String var10001 = this.prop;
      this.prop = var10001 + "#int";
      MainKt.setSideEffects(MainKt.getSideEffects() + "#fourth");
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
var sideEffects: String = ""

fun box(): String {
    val a1 = A("abc")
    if (a1.prop != "abc") return "fail1: ${a1.prop}"
    if (sideEffects != "first#second#third") return "fail1-sideEffects: ${sideEffects}"

    sideEffects = ""
    val a2 = A(123)
    if (a2.prop != "123#int") return "fail2: ${a2.prop}"
    if (sideEffects != "first#second#third#fourth") return "fail2-sideEffects: ${sideEffects}"

    sideEffects = ""
    val a3 = A()
    if (a3.prop != "") return "fail2: ${a3.prop}"
    if (sideEffects != "first#second") return "fail3-sideEffects: ${sideEffects}"
    return "OK"
}

