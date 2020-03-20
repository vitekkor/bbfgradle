//File D.java
import kotlin.Metadata;

public final class D {
   private int foo = 1;

   private final void setFoo(int i) {
      this.foo = i + 1;
   }

   public final void foo() {
      this.setFoo(5);
      int var10000 = this.foo;
   }
}


//File A.java
import kotlin.Metadata;

public final class A {
   private int foo = 1;

   private final int getFoo() {
      return 1;
   }

   public final void foo() {
      this.foo = 5;
      this.getFoo();
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
