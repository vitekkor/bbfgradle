//File Delegate.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Delegate {
   @NotNull
   public final Delegate provideDelegate(@Nullable Object instance, @NotNull KProperty property) {
      return this;
   }

   @NotNull
   public final String getValue(@Nullable Object instance, @NotNull KProperty property) {
      return "OK";
   }
}


//File Main.kt
import kotlin.reflect.KProperty

val result: String by Delegate()

fun box(): String {
    return result
}

