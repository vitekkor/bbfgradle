//File F2.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface F2 extends D1 {
   public static final class DefaultImpls {
      @NotNull
      public static D1 foo(F2 $this) {
         return D1.DefaultImpls.foo((D1)$this);
      }
   }
}


//File D1.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface D1 {
   @NotNull
   D1 foo();

   public static final class DefaultImpls {
      @NotNull
      public static D1 foo(D1 $this) {
         MainKt.setResult(MainKt.getResult() + "D1");
         return $this;
      }
   }
}


//File D4.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class D4 implements D3 {
   @NotNull
   public D3 foo() {
      return D3.DefaultImpls.foo(this);
   }
}


//File Main.kt
var result = ""

fun box(): String {
    val x = D4()
    x.foo()
    val d3: D3 = x
    val f2: F2 = x
    val d1: D1 = x
    d3.foo()
    f2.foo()
    d1.foo()
    return if (result == "D3D3D3D3") "OK" else "Fail: $result"
}



//File D3.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface D3 extends F2 {
   @NotNull
   D3 foo();

   public static final class DefaultImpls {
      @NotNull
      public static D3 foo(D3 $this) {
         MainKt.setResult(MainKt.getResult() + "D3");
         return $this;
      }
   }
}
