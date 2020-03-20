//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   private String result = "Fail";

   @NotNull
   public final String getResult() {
      return this.result;
   }

   public final void setResult(@NotNull String var1) {
      this.result = var1;
   }

   public final void foo(@NotNull String newResult) {
      this.result = newResult;
   }
}


//File Main.kt


fun box(): String {
    val a = A()
    val x = A::foo
    x(a, "OK")
    return a.result
}

