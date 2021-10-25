// Original bug: KT-23453

fun <T> T.mapIf(condition: Boolean, mapper: (T) -> T): T = if (condition) mapper(this) else this
fun <T> T.mapIf(predicate: (T) -> Boolean): ((T) -> T) -> T = { mapper: (T) -> T -> mapIf(predicate(this)) { mapper(this) } }

fun returnFun(fn: () -> Unit): (() -> Unit) -> Unit = TODO()
fun <X> simple(fn: (X) -> Unit) {}

fun context48() {
    returnFun {} () {}
    simple<String>() {}
    25.mapIf { true } () { it * 2 }
}
