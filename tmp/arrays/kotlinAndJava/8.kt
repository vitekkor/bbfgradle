//File Outer.java
import kotlin.Metadata;
import kotlin._Assertions;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class Outer {
   public static final Outer.Companion Companion = new Outer.Companion((DefaultConstructorMarker)null);

   public Outer() {
      boolean var1 = true;
      boolean var2 = false;
      boolean var3 = false;
      if (_Assertions.ENABLED) {
      }

   }

   static {
      String var0 = "";
      boolean var1 = false;
      throw (Throwable)(new IllegalStateException(var0.toString()));
   }

   public static final class Inner {
      public Inner() {
         boolean var1 = true;
         boolean var2 = false;
         boolean var3 = false;
         if (_Assertions.ENABLED) {
         }

      }
   }

   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM
// KOTLIN_CONFIGURATION_FLAGS: ASSERTIONS_MODE=jvm
// WITH_RUNTIME

fun box(): String {
    try {
        Outer.Inner()
    } catch (e: Throwable) {
        return "Fail"
    }
    return "OK"
}

