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
    try {
        r + "Try1"

        try {
            r + "Try2"
            if (true)
                return r
        } finally {
            r + "Finally2"
            if (true) {
                return r
            }
        }

        return r
    } finally {
        r + "Finally1"
    }
}


fun box(): String {
  return if (test1().toString() == "Try1Try2Finally2Finally1") "OK" else "fail: ${test1()}"
}

