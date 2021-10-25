// Original bug: KT-3535

class Foo {
    class Bar(val p: (Any) -> Any) {
        fun f() {
            p(1) // Unexpected error: Expression is inaccessible from a nested class 'Bar', use 'inner' keyword to make the class inner
        }
    }
}
