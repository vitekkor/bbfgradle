// Original bug: KT-16393

fun bar() {}

fun foo() {
    val f1 = ::bar
    val f2 = { bar() }
    val a = if (true) f1 else f2

    val b = if (true) ({ bar() }) else ::bar // <-- Type mismatch: inferred type is () -> Unit but KFunction0<Unit> was expected
}
