// Original bug: KT-25342

fun main(args: Array<String>) {
    val foo: Foo<String> by lazy {
        // Type inference failed: Not enough information to infer parameter T in fun <T> create(): Foo<T>
        // Please specify it explicitly.
        Foo.create()
    }
}

class Foo<T> {
    companion object {
        fun <T> create(): Foo<T> {
            return Foo()
        }
    }
}
