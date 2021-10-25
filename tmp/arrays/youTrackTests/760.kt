// Original bug: KT-43995

// foo.kts
fun <T> List<T>.bar(): Int = 7

class Foo() {
    fun foo() = listOf(0).bar()

    companion object {
        operator fun invoke(): Foo {
           return Foo()
        }
    }
}
