//File TestJava.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class TestJava implements Runnable {
   // $FF: synthetic field
   private final Runnable $$delegate_0;

   public TestJava(@NotNull Runnable r) {
      super();
      this.$$delegate_0 = r;
   }

   public void run() {
      this.$$delegate_0.run();
   }
}


//File TestRunnable.java
import kotlin.Metadata;

public final class TestRunnable implements Runnable {
   public void run() {
      System.out.println("foobar");
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// IGNORE_BACKEND: JS_IR
// Enable for JS when it supports Java class library.
// IGNORE_BACKEND: JS, NATIVE

fun box() : String {
    var del = TestJava(TestRunnable())
    del.run()
    if (del !is Runnable)
        return "Fail #1"

    return "OK"
}

