//File A.java
import kotlin.Metadata;

public final class A {
   public final void mem() {
   }
}


//File B.java
import kotlin.Metadata;

public final class B {
   public final void mem() {
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// IGNORE_BACKEND: JS, NATIVE

// WITH_REFLECT

import kotlin.test.*

fun top() = 42

fun Int.intExt(): Int = this


fun checkEqual(x: Any, y: Any) {
    assertEquals(x, y)
    assertEquals(x.hashCode(), y.hashCode(), "Elements are equal but their hash codes are not: ${x.hashCode()} != ${y.hashCode()}")
}

fun box(): String {
    checkEqual(::top, ::top)
    checkEqual(Int::intExt, Int::intExt)
    checkEqual(A::mem, A::mem)

    assertFalse(::top == Int::intExt)
    assertFalse(::top == A::mem)
    assertFalse(A::mem == B::mem)

    return "OK"
}

