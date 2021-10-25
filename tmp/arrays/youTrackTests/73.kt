// Original bug: KT-42832

fun Any.nothing(): Nothing {
    while (true) {}
}

fun test1(obj: Any?): Nothing? = obj?.nothing()

fun test2() {
    val block: (Any?) -> Nothing? = {
        it?.nothing()
    }
}
