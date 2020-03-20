//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   private String pp;

   @NotNull
   public final String getPp() {
      return this.pp;
   }

   public final void setPp(@NotNull String var1) {
      this.pp = var1;
   }

   public A(@NotNull String p) {
      super();
      this.pp = p;
      MainKt.setLog(MainKt.getLog() + "init(" + p + ");");
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
var log = ""

operator fun A.plusAssign(s: String) {
    pp += s
    log += "pp = $pp;"
}

inline fun <T, R> T.foo(f: (T) -> R) = f(this)

fun <T, R> T.bar(f: (T) -> R) = f(this)

fun box(): String {
    "rrr".foo { A(it) } += "aaa"

    if (log != "init(rrr);pp = rrraaa;") return "1: log = \"$log\""

    log = ""
    "foo".bar { A(it) } += "baaar"

    if (log != "init(foo);pp = foobaaar;") return "2: log = \"$log\""

    return "OK"
}

