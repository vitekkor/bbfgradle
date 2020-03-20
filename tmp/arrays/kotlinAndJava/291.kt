//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   private final String z;
   @NotNull
   private final String s;

   @NotNull
   public final String getZ() {
      return this.z;
   }

   @NotNull
   public final String test() {
      return this.s;
   }

   @NotNull
   public final String getS() {
      return this.s;
   }

   public A(@NotNull String s) {
      super();
      this.s = s;
      this.z = this.s;
   }

   public class B extends A {
      @NotNull
      public final String testB() {
         return this.getZ() + this.test();
      }

      public B(@NotNull String s) {
         super(s);
      }
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR

fun box(): String {
    val res = A("Fail").B("OK").testB()
    return if (res == "OKOK") "OK" else res;
}

