//File MyString.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class MyString {
   @NotNull
   private String s = "";

   @NotNull
   public final String getS() {
      return this.s;
   }

   public final void setS(@NotNull String var1) {
      this.s = var1;
   }

   @NotNull
   public final MyString plus(@NotNull String x) {
      String var10001 = this.s;
      this.s = var10001 + x;
      return this;
   }

   @NotNull
   public String toString() {
      return this.s;
   }
}


//File Main.kt


fun test1() : MyString {
    var r = MyString()
    while (true) {
      try {
          r + "Try"

          if (true) {
              r + "Break"
              break
          }

      } finally {
          return r + "Finally"
      }
    }
}



fun box(): String {
    if (test1().toString() != "TryBreakFinally") return "fail1: ${test1().toString()}"

    return "OK"
}

