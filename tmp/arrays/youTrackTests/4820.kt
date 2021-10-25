// Original bug: KT-33553

fun top() = "".plus("").plus("")

class C {
    fun member() = "".plus("").plus("")
}

fun foo() {
    fun local() = "".plus("").plus("")
    val anonymous = fun() = "".plus("").plus("")
}
