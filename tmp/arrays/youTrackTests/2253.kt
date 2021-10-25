// Original bug: KT-40260

class Foo<F>

class Bar<F> {
    suspend operator fun Foo<F>.not() = true
}

suspend fun <F> main(foo: (suspend (Foo<F>) -> Unit) -> Unit, bar: (suspend Bar<F>.() -> Unit) -> Unit) {
    foo { p1 ->
        bar {
            !p1
        }
    }
}
