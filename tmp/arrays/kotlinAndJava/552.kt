//File UserDataProperty.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class UserDataProperty {
   @NotNull
   private final String key;

   @NotNull
   public final String getValue(Object thisRef, @NotNull KProperty desc) {
      return thisRef + this.key;
   }

   public final void setValue(Object thisRef, @NotNull KProperty desc, @Nullable String value) {
      MainKt.setLog(MainKt.getLog() + "set");
   }

   @NotNull
   public final String getKey() {
      return this.key;
   }

   public UserDataProperty(@NotNull String key) {
      super();
      this.key = key;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
import kotlin.reflect.KProperty

var log = ""


var String.calc: String by UserDataProperty("K")

fun box(): String {
    return "O".calc
}

