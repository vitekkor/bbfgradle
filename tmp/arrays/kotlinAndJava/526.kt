//File Delegate.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Delegate {
   @NotNull
   public final String getValue(@Nullable Object thisRef, @NotNull KProperty prop) {
      return "OK";
   }
}


//File Main.kt
import kotlin.reflect.KProperty

// KT-5612

val prop by Delegate()

val a = prop

fun box() = a

