// Original bug: KT-5464

interface Foo<T : Foo<T>> {
    fun <B : T> apply(): B
}

// You can create extension function like this:
fun <T : Foo<T>> Foo<*>.applyIt(): Foo<*> = (this as Foo<T>).apply()

//and call it like this:
fun test(f: Foo<*>) {
    f.applyIt<Nothing>()
}
