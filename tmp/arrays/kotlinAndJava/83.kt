//File Delegate.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Delegate {
   private int inner = 1;

   public final int getInner() {
      return this.inner;
   }

   public final void setInner(int var1) {
      this.inner = var1;
   }

   public final int getValue(@Nullable Object t, @NotNull KProperty p) {
      return this.inner;
   }

   public final void setValue(@Nullable Object t, @NotNull KProperty p, int i) {
      this.inner = i;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
import kotlin.reflect.KProperty

var prop: Int by Delegate()

fun box(): String {
  if(prop != 1) return "fail get"
  prop = 2
  if (prop != 2) return "fail set"
  return "OK"
}

