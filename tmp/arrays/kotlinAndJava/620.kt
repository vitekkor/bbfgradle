//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// WITH_RUNTIME

fun box(): String {
    var x = Host("")
    run {
        x[0, 0, 0] += "O"
        x[0, 0, 0] += "K"
    }
    return x.value
}



//File Host.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Host {
   @NotNull
   private String value;

   @NotNull
   public final String get(int i, int j, int k) {
      return this.value;
   }

   public final void set(int i, int j, int k, @NotNull String newValue) {
      this.value = newValue;
   }

   @NotNull
   public final String getValue() {
      return this.value;
   }

   public final void setValue(@NotNull String var1) {
      this.value = var1;
   }

   public Host(@NotNull String value) {
      super();
      this.value = value;
   }
}
