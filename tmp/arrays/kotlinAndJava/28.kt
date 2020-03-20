//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR

fun box() : String {
    if(C().testReceiver() != "111 222") return "fail"
    return "OK"
}



//File C.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class C {
   @NotNull
   public final String toMyPrefixedString(@NotNull Object $this$toMyPrefixedString, @NotNull String prefix, @NotNull String suffix) {
      return prefix + " " + suffix;
   }

   // $FF: synthetic method
   public static String toMyPrefixedString$default(C var0, Object var1, String var2, String var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var2 = "";
      }

      if ((var4 & 2) != 0) {
         var3 = "";
      }

      return var0.toMyPrefixedString(var1, var2, var3);
   }

   @NotNull
   public final String testReceiver() {
      String res = this.toMyPrefixedString("mama", "111", "222");
      return res;
   }
}
