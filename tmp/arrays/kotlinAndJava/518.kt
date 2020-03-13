//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// WITH_RUNTIME

fun box(): String {
    val a = KInt::class.java.getField("a").get(null)
    val b = KInt::class.java.getField("b").get(null)

    if (a !== KInt.a) return "fail 1: KInt.a !== KInt.Companion.a"
    if (b !== KInt.b) return "fail 2: KInt.b !== KInt.Companion.b"
    if (b !== "ba") return "fail 2: 'ba' !== KInt.Companion.b"

    return "OK"
}



//File KInt.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface KInt {
   KInt.Companion Companion = KInt.Companion.$$INSTANCE;
   @NotNull
   String a = "a";
   @NotNull
   String b = "ba";

   public static final class Companion {
      @NotNull
      public static final String a = "a";
      @NotNull
      public static final String b = "ba";
      // $FF: synthetic field
      static final KInt.Companion $$INSTANCE;

      private Companion() {
      }

      static {
         KInt.Companion var0 = new KInt.Companion();
         $$INSTANCE = var0;
      }
   }
}
