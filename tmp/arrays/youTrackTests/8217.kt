// Original bug: KT-11945

class Foo(a: Int = A) { // Can't navigate to A from here
    companion object {
        const val A = 1 // Incorrect warning that A is never used
    }
}
