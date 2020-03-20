//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   String foo();
}


//File Derived1.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class Derived1 extends Base {
   @NotNull
   private final A a;

   @NotNull
   public A getA() {
      return this.a;
   }

   public Derived1(@NotNull String p) {
      super(p);
      this.a = (A)(new A() {
         @NotNull
         public String foo() {
            return "fail";
         }
      });
   }

   public final class Derived2 extends Base {
      @NotNull
      private final A x;

      @NotNull
      public final A getX() {
         return this.x;
      }

      public Derived2(@NotNull String p) {
         super(p);
         this.x = (A)(new A() {
            // $FF: synthetic field
            private final A $$delegate_0 = Derived1.super.getA();

            @NotNull
            public String foo() {
               return this.$$delegate_0.foo();
            }
         });
      }
   }
}


//File Base.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class Base {
   @NotNull
   private final A a;
   @NotNull
   private final String p;

   @NotNull
   public A getA() {
      return this.a;
   }

   @NotNull
   public final String getP() {
      return this.p;
   }

   public Base(@NotNull String p) {
      super();
      this.p = p;
      this.a = (A)(new A() {
         @NotNull
         public String foo() {
            return Base.this.getP();
         }
      });
   }
}


//File Main.kt


fun box(): String {
    return Derived1("OK").Derived2("fail").x.foo()
}

