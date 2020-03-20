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
// IGNORE_BACKEND_FIR: JVM_IR
//KT-3869 Loops and finally: outer finally block not run

fun test1() : MyString {
    var r = MyString()
    try {
        r + "Try"

        while(r.toString() != "") {
            return r + "Loop"
        }

        return r + "Fail"
    } finally {
        r + "Finally"
    }
}

fun test2() : MyString {
    var r = MyString()
    try {
        r + "Try"

        do {
            if (r.toString() != "") {
                return r + "Loop"
            }
        } while (r.toString() != "")

        return r + "Fail"
    } finally {
        r + "Finally"
    }
}

fun test3() : MyString {
    var r = MyString()
    try {
        r + "Try"

        for(i in 1..2) {
            r + "Loop"
            return r
        }

        return r + "Fail"
    } finally {
        r + "Finally"
    }
}

fun box(): String {
    if (test1().toString() != "TryLoopFinally") return "fail1: ${test1()}"
    if (test2().toString() != "TryLoopFinally") return "fail2: ${test2()}"
    if (test3().toString() != "TryLoopFinally") return "fail3: ${test3()}"

    return "OK"
}

