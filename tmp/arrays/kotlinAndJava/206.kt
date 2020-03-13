//File A.java
import kotlin.Metadata;

public interface A {
   int foo();
}


//File B.java
import kotlin.Metadata;

public interface B {
   int foo();
}


//File Z.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Z implements A, B {
   @NotNull
   private final A a;

   @NotNull
   public final A getA() {
      return this.a;
   }

   public Z(@NotNull A a) {
      super();
      this.a = a;
   }

   public int foo() {
      return this.a.foo();
   }
}


//File Main.kt


fun box(): String {
    val s = Z(object : A {
        override fun foo(): Int {
            return 1;
        }
    });
    return if (s.foo() == 1) "OK" else "fail"
}

