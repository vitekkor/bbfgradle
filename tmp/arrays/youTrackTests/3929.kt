// Original bug: KT-37463

class Foo {
    fun String.foo() = toUpperCase()
}

fun main() {
    val f: Foo? = Foo()
    with(f) {

        if (this != null) {
            "hello world".foo()
        }
    }
}
