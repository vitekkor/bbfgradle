// Original bug: KT-12152

class Outer {
    fun Nested.foo(): String = x

    class Nested(outer: Outer) {
        val x: String = with(outer) { foo() } // OOPS! x also holds null
    }
}
