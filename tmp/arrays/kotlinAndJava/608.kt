//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   private A prop;
   private final int p;

   @NotNull
   public final A getProp() {
      return this.prop;
   }

   public final void setProp(@NotNull A var1) {
      this.prop = var1;
   }

   @NotNull
   public final A inc() {
      return new A(this.p + 1);
   }

   public final int getP() {
      return this.p;
   }

   public A(int p) {
      this.p = p;
      this.prop = (A)this;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
var holder = ""
var globalA: A = A(-1)
    get(): A {
        holder += "getA"
        return field
    }


fun box(): String {
    var a = A(1)
    ++a
    if (a.p != 2) return "fail 1: ${a.p} $holder"

    globalA = A(1)
    ++(globalA.prop)
    val holderValue = holder;
    if (globalA.p != 1 || globalA.prop.p != 2 || holderValue != "getA") return "fail 2: ${a.p} ${a.prop.p} ${holderValue}"

    return "OK"
}

