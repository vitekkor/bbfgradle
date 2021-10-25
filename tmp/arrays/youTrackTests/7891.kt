// Original bug: KT-23752

fun test() {
    val a: (String) -> Unit = { s -> println(s) } // Remove variable 'a'
    val b: (String) -> Unit = { s: String -> println(s) } // Remove variable 'b'

    val c: (String) -> Unit = fun(s) { println(s) } // Remove variable 'c'
    val d: (String) -> Unit = fun(s: String) { println(s) } // Remove variable 'd'
}
