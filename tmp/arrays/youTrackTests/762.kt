// Original bug: KT-43995

// foo.kts
fun bar(): Int = 7

class Foo() {
    fun foo() = bar()
    companion object {
        operator fun invoke(): Foo {
            return Foo()
        }
    }
}
