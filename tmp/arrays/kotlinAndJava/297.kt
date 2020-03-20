//File IntArrayList.java
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class IntArrayList extends ArrayList {
   @NotNull
   public Integer get(int index) {
      Object var10000 = super.get(index);
      return (Integer)var10000;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// KJS_WITH_FULL_RUNTIME
// IGNORE_BACKEND: NATIVE

fun box(): String {
    val a = IntArrayList()
    a.add(1)
    a[0]++
    return if (a[0] == 2) "OK" else "fail"
}

