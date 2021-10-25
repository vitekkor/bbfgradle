// Original bug: KT-36348

fun <T> anotherMethod(a: T, block: () -> Unit) {}
@Deprecated("Use anotherMethod instead", ReplaceWith("anotherMethod(a, block)"))
fun <T> aMethod(a: T, block: () -> Unit) {
}

fun main() {
    aMethod(1) {} // after applying: anotherMethod(1, {})  
}
