//File A.java
import kotlin.Metadata;

public class A {
   public Object foo(Object r) {
      return r;
   }
}


//File B.java
import kotlin.Metadata;

public class B extends A {
}


//File Main.kt


fun box() = C().foo("O")



//File C.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class C extends B {
   @NotNull
   public String foo(@NotNull String r) {
      return (String)super.foo(r) + "K";
   }
}
