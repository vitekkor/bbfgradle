//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A extends B {
   @NotNull
   private String prop;

   @NotNull
   public final String getProp() {
      return this.prop;
   }

   public final void setProp(@NotNull String var1) {
      this.prop = var1;
   }

   public A(int x1, int x2) {
      super(x1, x2);
      this.prop = "";
      MainKt.setSideEffects(MainKt.getSideEffects() + this.prop + "first");
      MainKt.setSideEffects(MainKt.getSideEffects() + this.prop + "#second");
      this.prop = String.valueOf(x1);
      MainKt.setSideEffects(MainKt.getSideEffects() + "#third");
   }

   public A(int x) {
      super(3 + x);
      this.prop = "";
      MainKt.setSideEffects(MainKt.getSideEffects() + this.prop + "first");
      MainKt.setSideEffects(MainKt.getSideEffects() + this.prop + "#second");
      String var10001 = this.prop;
      this.prop = var10001 + x + "#int";
      MainKt.setSideEffects(MainKt.getSideEffects() + "#fourth");
   }

   public A() {
      this(7);
      MainKt.setSideEffects(MainKt.getSideEffects() + "#fifth");
   }
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public abstract class B {
   @NotNull
   private final String parentProp;

   @NotNull
   public final String getParentProp() {
      return this.parentProp;
   }

   protected B(int arg) {
      MainKt.setSideEffects(MainKt.getSideEffects() + "minus-one#");
      MainKt.setSideEffects(MainKt.getSideEffects() + "zero#");
      this.parentProp = String.valueOf(arg);
      MainKt.setSideEffects(MainKt.getSideEffects() + "0.5#");
   }

   protected B(int arg1, int arg2) {
      MainKt.setSideEffects(MainKt.getSideEffects() + "minus-one#");
      MainKt.setSideEffects(MainKt.getSideEffects() + "zero#");
      this.parentProp = String.valueOf(arg1 + arg2);
      MainKt.setSideEffects(MainKt.getSideEffects() + "0.7#");
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
var sideEffects: String = ""

fun box(): String {
    val a1 = A(5, 10)
    if (a1.prop != "5") return "fail0: ${a1.prop}"
    if (a1.parentProp != "15") return "fail1: ${a1.parentProp}"
    if (sideEffects != "minus-one#zero#0.7#first#second#third") return "fail2: ${sideEffects}"

    sideEffects = ""
    val a2 = A(123)
    if (a2.prop != "123#int") return "fail3: ${a2.prop}"
    if (a2.parentProp != "126") return "fail4: ${a2.parentProp}"
    if (sideEffects != "minus-one#zero#0.5#first#second#fourth") return "fail5: ${sideEffects}"

    sideEffects = ""
    val a3 = A()
    if (a3.prop != "7#int") return "fail6: ${a3.prop}"
    if (a3.parentProp != "10") return "fail7: ${a3.parentProp}"
    if (sideEffects != "minus-one#zero#0.5#first#second#fourth#fifth") return "fail8: ${sideEffects}"
    return "OK"
}

