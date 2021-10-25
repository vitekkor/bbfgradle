// Original bug: KT-21862

class Foo {
    companion object {
        lateinit var foo: String

        fun bar() {
            println(::foo.isInitialized)
        }
    }
}

fun main(args: Array<String>) {
    Foo.bar()
}
