// Original bug: KT-43995

// foo.kts
fun <T> List<T>.bar(): Int = 7
class Foo() {
    companion object {
        operator fun invoke(): Foo {
            return Foo()
        }
    }
}

fun (Foo).foo() = listOf(0).bar()
