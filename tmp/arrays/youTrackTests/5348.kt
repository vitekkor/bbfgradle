// Original bug: KT-33217

class A {
    @Deprecated("Deprecation message")
    companion object Named {
        val param = 42
    }
}

fun call() {
    A.Named // `Named` marked as deprecated. Hover the mouse to see the hint message
}
