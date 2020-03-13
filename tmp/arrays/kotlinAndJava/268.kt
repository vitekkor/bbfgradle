//File Delegate.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Delegate {
   @NotNull
   private final String value;

   @NotNull
   public final String getValue(@Nullable Object thisRef, @Nullable Object kProperty) {
      return this.value;
   }

   @NotNull
   public final String getValue() {
      return this.value;
   }

   public Delegate(@NotNull String value) {
      super();
      this.value = value;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR

fun box(): String {
    val x by Delegate("O")

    class Local(val y: String) {
        val fn = { x + y }
    }

    return Local("K").fn()
}

