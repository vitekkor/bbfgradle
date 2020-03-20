//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public interface A {
   @NotNull
   String getFoo();

   void setFoo(@NotNull String var1);

   public static final class DefaultImpls {
      @NotNull
      public static String getFoo(A $this) {
         return MainKt.getResult();
      }

      public static void setFoo(A $this, @NotNull String value) {
         MainKt.setResult(value);
      }
   }
}


//File B.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public interface B extends A {
   public static final class DefaultImpls {
      @NotNull
      public static String getFoo(B $this) {
         return A.DefaultImpls.getFoo((A)$this);
      }

      public static void setFoo(B $this, @NotNull String value) {
         A.DefaultImpls.setFoo((A)$this, value);
      }
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
var result = "Fail"

fun box(): String {
    C().foo = "OK"
    return C().foo
}



//File C.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class C implements B {
   @NotNull
   public String getFoo() {
      return B.DefaultImpls.getFoo(this);
   }

   public void setFoo(@NotNull String value) {
      B.DefaultImpls.setFoo(this, value);
   }
}
