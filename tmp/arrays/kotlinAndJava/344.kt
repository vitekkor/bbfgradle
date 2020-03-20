//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A implements Intf {
   @NotNull
   public String str;

   @NotNull
   public String getStr() {
      String var10000 = this.str;
      if (var10000 == null) {
         }

      return var10000;
   }

   public void setStr(@NotNull String var1) {
      this.str = var1;
   }

   public final void setMyStr() {
      this.setStr("OK");
   }

   @NotNull
   public final String getMyStr() {
      return this.getStr();
   }
}


//File Main.kt


fun box(): String {
    val a = A()
    a.setMyStr()
    return a.getMyStr()
}



//File Intf.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface Intf {
   @NotNull
   String getStr();
}
