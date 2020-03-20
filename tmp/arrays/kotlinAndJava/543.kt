//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   public final String bar() {
      return "";
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// WITH_REFLECT

import kotlin.test.assertEquals

fun foo() {}

fun Int.baz() = this

fun box(): String {
    assertEquals("foo", ::foo.name)
    assertEquals("bar", A::bar.name)
    assertEquals("baz", Int::baz.name)
    return "OK"
}

