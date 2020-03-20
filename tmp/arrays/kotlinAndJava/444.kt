//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   private final String s;

   @NotNull
   public final String getS() {
      return this.s;
   }

   public A(@NotNull String s) {
      super();
      this.s = s;
   }
}


//File Main.kt


inline fun test(crossinline z: () -> String): String {
    return object : A(listOf(1).map { it.toString() }.joinToString()) {
        val value = z()
    }.value
}

fun box(): String {
    return test { "OK" }
}

