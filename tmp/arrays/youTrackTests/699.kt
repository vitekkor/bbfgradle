// Original bug: KT-43643

fun foo(a: Any? = null, b: Any? = null) {
    require(a != null || b != null) {
        "At least one of 'a' or 'b' must be given."
    }
}

fun bar() {
    foo() // Should show warning "At least one of 'a' or 'b' must be given."
}
