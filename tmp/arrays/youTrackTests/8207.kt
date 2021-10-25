// Original bug: KT-11824

data class Foo(val id: String) { // 'data' is important
    fun getId() = -42 // Fail
}

fun main(args: Array<String>) {
    println(Foo::id.call(Foo("OK")))
}
