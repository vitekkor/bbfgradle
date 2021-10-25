// Original bug: KT-42037

// E.g. runBlocking
// If block is not suspending, the compiler does not crash.
fun outerScope(block: suspend () -> Unit) {}

// If block does not have a receiver, the compiler does not crash.
// If the function does not have a type parameter, the compiler does not crash.
fun <T> repro() {
    outerScope {
        // If this object is defined outside outerScope, the compiler does not crash.
        val foo = object {
            fun bar() {}
        }

        foo.bar()
    }
}
