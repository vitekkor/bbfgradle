// Original bug: KT-27641

inline fun <reified T> foo() = of(T::class.java) // inferred return type `Foo<T>`, but intention inserts `Any`

class Foo<T>
fun <T> of(c: Class<T>): Foo<T> = Foo()
