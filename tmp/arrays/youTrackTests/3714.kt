// Original bug: KT-34433

class Foo<out T>(val baz: Baz<T>)

class Bar {
    val foo: Foo<*> = TODO()

    fun <T> bar(): Baz<T> {
        return foo.baz // Type mismatch: inferred type is (Nothing) -> Unit but Baz<T> /* = (T) -> Unit */ was expected
    }
}

typealias Baz<T> = (@UnsafeVariance T) -> Unit
