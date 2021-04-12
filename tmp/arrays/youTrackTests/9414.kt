// Original bug: KT-8990

// Kotlin
abstract class A {
    private fun foo() {
    }

    abstract inner class B : A() {
        private fun foo() { // Error: 'foo' hides member of supertype 'A' and needs 'override' modifier
        }
    }
}
