//File MyClass.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class MyClass {
   @NotNull
   private final String value;

   @NotNull
   public String toString() {
      return this.value;
   }

   @NotNull
   public final String getValue() {
      return this.value;
   }

   public MyClass(@NotNull String value) {
      super();
      this.value = value;
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// WITH_RUNTIME

import kotlin.test.*

var log: String = ""

inline fun <L> runLogged(entry: String, action: () -> L): L {
    log += entry
    return action()
}

operator fun <P: MyClass> P.provideDelegate(host: Any?, p: Any): P =
        runLogged("tdf(${this.value});") { this }

operator fun <V> V.getValue(receiver: Any?, p: Any): V =
        runLogged("get($this);") { this }

val testO by runLogged("O;") { MyClass("O") }
val testK by runLogged("K;") { "K" }
val testOK = runLogged("OK;") { testO.value + testK }

fun box(): String {
    assertEquals("O;tdf(O);K;OK;get(O);get(K);", log)
    return testOK
}

