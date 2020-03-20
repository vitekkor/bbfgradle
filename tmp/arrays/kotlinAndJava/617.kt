//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   private String value;

   @NotNull
   public final String getValue() {
      return this.value;
   }

   public final void setValue(@NotNull String var1) {
      this.value = var1;
   }

   public A(@NotNull String value) {
      super();
      this.value = value;
   }
}


//File Main.kt


fun box(): String {
    val a = A("start")

    try {
        test(a)
    } catch(e: RuntimeException) {

    }

    if (a.value != "start, try, finally1, finally2") return "fail: ${a.value}"

    return "OK"
}

fun test(a: A) : String {
    while (a.value == "start") {
        try {
            try {
                a.value += ", try"
                continue
            }
            finally {
                a.value += ", finally1"
            }
        }
        finally {
            a.value += ", finally2"
            throw RuntimeException("fail")
        }
    }
    return "fail"
}

