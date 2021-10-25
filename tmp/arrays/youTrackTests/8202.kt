// Original bug: KT-22748

inline fun xassert(condition: Boolean, messageCallback: () -> Any = { "Assertion failed" }) {
    if (!condition) {
        throw AssertionError(messageCallback())
    }
}
