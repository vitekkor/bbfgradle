//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   private String str;

   @NotNull
   public final String getMyStr() {
      try {
         if (this.str == null) {
            }

         return "FAIL";
      } catch (RuntimeException var2) {
         return "OK";
      }
   }
}


//File Main.kt


fun box(): String {
    val a = A()
    return a.getMyStr()
}

