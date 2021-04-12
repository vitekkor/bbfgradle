// Original bug: KT-8780

fun foo(x : String?, y : String?) {
    if (y != null && x == y) {
        x.toLowerCase() // Error: Only safe or non-null asserted calls are allowed on a nullable receiver
    }
}
