// Original bug: KT-36348

fun anotherMethod(a: Int, block: () -> Unit) {}
@Deprecated("Use anotherMethod instead", ReplaceWith("anotherMethod(a, block)"))
fun aMethod(a: Int, block: () -> Unit) {
}

fun main() {
    anotherMethod(1) {} // after applying: anotherMethod(1) {}
}
