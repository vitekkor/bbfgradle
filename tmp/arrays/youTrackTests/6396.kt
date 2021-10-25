// Original bug: KT-30405

import kotlin.test.assertEquals

inline fun <reified T> foo(): T {
    return T::class.java.getName() as T
}

fun box(): String {
    val fooCall = foo() as String // T in foo should be inferred to String
    assertEquals("java.lang.String", fooCall)

    val safeFooCall = foo() as? String
    assertEquals("java.lang.String", safeFooCall)

    return "OK"
}
