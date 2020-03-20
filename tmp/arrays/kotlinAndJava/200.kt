//File B.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class B implements MyTrait {
   @NotNull
   private String property;

   @NotNull
   public String getProperty() {
      return this.property;
   }

   public void setProperty(@NotNull String var1) {
      this.property = var1;
   }

   public void foo() {
      MyTrait.DefaultImpls.foo(this);
   }

   public B(@NotNull String param) {
      super();
      this.property = param;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
var result = "Fail"

fun box(): String {
    val b = B("OK")
    b.foo()
    return result
}



//File MyTrait.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface MyTrait {
   @NotNull
   String getProperty();

   void setProperty(@NotNull String var1);

   void foo();

   public static final class DefaultImpls {
      public static void foo(MyTrait $this) {
         MainKt.setResult($this.getProperty());
      }
   }
}
