//File A.java
import kotlin.Metadata;

public final class A {
}


//File Delegate.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

public final class Delegate {
   public final int getValue(@NotNull A t, @NotNull KProperty p) {
      return 1;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
import kotlin.reflect.KProperty

val A.prop: Int by Delegate()

fun box(): String {
  return if(A().prop == 1) "OK" else "fail"
}

