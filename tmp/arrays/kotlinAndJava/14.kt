//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   public String str;

   @NotNull
   public final String getStr() {
      String var10000 = this.str;
      if (var10000 == null) {
         }

      return var10000;
   }

   public final void setStr(@NotNull String var1) {
      this.str = var1;
   }
}


//File Main.kt


fun box(): String {
    val a = A()
    a.str = "OK"
    return a.str
}

