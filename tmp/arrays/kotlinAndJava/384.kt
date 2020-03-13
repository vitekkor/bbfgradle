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

   public class B {
      @NotNull
      private final String s;

      @NotNull
      public final String testB() {
         return this.s + A.this.getS();
      }

      @NotNull
      public final String getS() {
         return this.s;
      }

      public B(@NotNull String s) {
         super();
         this.s = s;
      }
   }

   public class C extends A {
      @NotNull
      public final String testC() {
         return (new A.B("B_")).testB();
      }

      public C() {
         super("C");
      }
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR

fun box(): String {
    val res = A("A").C().testC()
    return if (res == "B_C") "OK" else res;
}

