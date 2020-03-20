//File B.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public interface B {
   public static final class Z {
      public final void ztest(@NotNull B b) {
         B.DefaultImpls.test(b);
      }
   }

   public static final class DefaultImpls {
      private static void test(B $this) {
         MainKt.setResult("OK");
      }
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
var result = "fail"

fun box(): String {
    B.Z().ztest(C())
    return result
}



//File C.java
import kotlin.Metadata;

public final class C implements B {
}
