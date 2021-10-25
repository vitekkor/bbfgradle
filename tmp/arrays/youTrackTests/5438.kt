// Original bug: KT-30684

fun fail(): Nothing {
    throw IllegalArgumentException("fail")
}
fun use() {
    if (true) {
        return
    } else {
        fail()  // to be highlighted as exit point together with the `return` above
    }
}
