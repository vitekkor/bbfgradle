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

   public class B extends A {
      public B(@NotNull String s) {
         super(s);
      }
   }

   public class C extends A.B {
      public C(@NotNull String s, double additional) {
         super(s);
      }
   }

   public class D extends A.C {
      public D(int other, long another, @NotNull String s) {
         super(s, (double)another);
      }
   }

   public class E extends A.D {
      public E() {
         super(0, 42L, "OK");
      }
   }

   public final class F extends A.E {
      public F() {
         super();
      }
   }
}


//File Main.kt


fun box(): String = A("Fail").F().s

