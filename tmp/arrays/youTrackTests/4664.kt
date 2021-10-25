// Original bug: KT-35666

open class Foo() {
    companion object {
        object A : Foo()
        val ALL_TYPES = A
    }
}

fun main() {
    // The following line causes error - if comment the line, it works fine
    Foo.Companion.A

    println(Foo.ALL_TYPES) // null
}
