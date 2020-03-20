//File MyClass.java
import kotlin.Metadata;

public class MyClass {
   public final int def(int i) {
      return i;
   }

   // $FF: synthetic method
   public static int def$default(MyClass var0, int var1, int var2, Object var3) {
      if (var3 != null) {
         throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: def");
      } else {
         if ((var2 & 1) != 0) {
            var1 = 0;
         }

         return var0.def(var1);
      }
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// IGNORE_BACKEND: JVM_IR
// TARGET_BACKEND: JVM
// WITH_RUNTIME

fun box():String {
    val method = MyClass::class.java.getMethod("def\$default", MyClass::class.java, Int::class.java, Int::class.java, Any::class.java)
    val result = method.invoke(null, MyClass(), -1, 1, null)

    if (result != 0) return "fail 1: $result"

    var failed = false
    try {
        method.invoke(null, MyClass(), -1, 1, "fail")
    }
    catch(e: Exception) {
        val cause = e.cause
        if (cause is UnsupportedOperationException && cause.message!!.startsWith("Super calls")) {
            failed = true
        }
    }

    return if (!failed) "fail" else "OK"
}

