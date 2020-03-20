//File ZImpl3.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class ZImpl3 extends ZImpl2 {
   @NotNull
   public String test(@NotNull String p, @NotNull String z) {
      return (String)super.test(p, z);
   }
}


//File ZImpl.java
import kotlin.Metadata;

public class ZImpl extends Z {
}


//File Z.java
import kotlin.Metadata;

public class Z {
   public Object test(Object p, Object z) {
      return p;
   }
}


//File ZImpl2.java
import kotlin.Metadata;

public class ZImpl2 extends ZImpl {
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR

fun box(): String {
    return ZImpl3().test("OK", "fail")
}

