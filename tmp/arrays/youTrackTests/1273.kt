// Original bug: KT-16445

interface SomeInterface<T>

object Container {
    private inline fun <reified T> someMethod() = object : SomeInterface<T> { }
    class SomeClass : SomeInterface<SomeClass> by someMethod()
}

fun main(args: Array<String>) {
    Container.SomeClass()
}
