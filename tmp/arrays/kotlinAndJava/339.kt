//File A.java
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   public static final A INSTANCE;

   @JvmStatic
   public static final void main(@NotNull String[] args) {
      final String[][] b = (String[][])(new String[][]{{""}});
      Object var10001 = new Object() {
         @NotNull
         private final String[] c = b[0];

         @NotNull
         public final String[] getC() {
            return this.c;
         }
      };
   }

   private A() {
   }

   static {
      A var0 = new A();
      INSTANCE = var0;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
//WITH_RUNTIME
// TARGET_BACKEND: JVM

fun box(): String {
    A.main(emptyArray())
    return "OK"
}

