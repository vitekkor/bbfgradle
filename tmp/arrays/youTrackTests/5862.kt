// Original bug: KT-29702

class B {
    fun foo(before: String) {
        bar(before)
    }

    fun bar(after: String) {
        baz(after) // "extract parameter" on the whole function call
    }

    fun baz(x: String) = x
}
