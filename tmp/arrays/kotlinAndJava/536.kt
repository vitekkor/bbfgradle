//File D.java
import kotlin.Metadata;

public final class D {
   private int foo = 1;

   public final int getFoo() {
      return this.foo;
   }

   public final void foo() {
      this.foo = 2;
   }
}


//File A.java
import kotlin.Metadata;

public final class A {
   private final void f1() {
   }

   public final void foo() {
      this.f1();
   }
}


//File B.java
import kotlin.Metadata;

public final class B {
   private final int foo = 1;

   public final void foo() {
      int var10000 = this.foo;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// IGNORE_BACKEND: JS_IR
// TODO: muted automatically, investigate should it be ran for JS or not
// IGNORE_BACKEND: JS

// KT-2202 Wrong instruction for invoke private setter

fun box(): String {
   A().foo()
   B().foo()
   C().foo()
   D().foo()
   return "OK"
}



//File C.java
import kotlin.Metadata;

public final class C {
   private int foo = 1;

   public final void foo() {
      this.foo = 2;
      int var10000 = this.foo;
   }
}
