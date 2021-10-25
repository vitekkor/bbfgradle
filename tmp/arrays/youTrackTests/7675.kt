// Original bug: KT-25948

fun test_2(a: Any): Boolean {
    return a.hashCode() % 2 == 0
}

fun main(args : Array<String>) {
    test_2("1") && return
    test_2("2") || throw Exception()
    println("1")
}
