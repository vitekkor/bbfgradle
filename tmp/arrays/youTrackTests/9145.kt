// Original bug: KT-15964

interface TestA<out T> {
    fun foo(): T
}

abstract class TestB : TestA<Unit?> {
    override fun foo(): Unit? {
        // ...
        return Unit
    }
}
