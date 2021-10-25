// Original bug: KT-8970

open class Foo() {
    companion object {
        object A : Foo()
        val ALL_TYPES = A
    }
}

fun main() {
    // The following line causes error - if the line is commented, it works fine
    Foo.Companion.A

    println(Foo.ALL_TYPES) // null
}
