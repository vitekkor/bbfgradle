// Original bug: KT-34308

fun f() {
    val list : MutableList<String> = mutableListOf()
    g(ref = list::add) // green in IDE, fails on command line
    g(list::add) // green in IDE, fails on command line
}

fun g(ref: (String) -> Unit) = Unit
