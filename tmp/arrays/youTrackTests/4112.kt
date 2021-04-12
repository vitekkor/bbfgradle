// Original bug: KT-29911

fun <T> T.foo(block: T.() -> Unit) {}

fun <T : Number?> bar(t: T) {
    t?.foo { toInt() } // UNSAFE_CALL
    if (t != null) t.foo { toInt() } // UNSAFE_CALL
    if (t != null) t.foo { this.toInt() } // UNSAFE_CALL
}

fun baz(t: Number?) {
    t?.foo { toInt() } // OK
}
