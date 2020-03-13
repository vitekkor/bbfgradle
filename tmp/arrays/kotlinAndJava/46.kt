//File Delegate.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Delegate {
   public final int getValue(@Nullable Object t, @NotNull KProperty p) {
      return 1;
   }
}


//File Main.kt
import kotlin.reflect.KProperty

val prop: Int by Delegate()

fun box(): String {
  return if(prop == 1) "OK" else "fail"
}

