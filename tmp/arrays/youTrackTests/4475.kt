// Original bug: KT-36337

fun <T : Int> anotherMethod(a: T, b: T = (a * 2) as T, block: () -> Unit) {}

@Deprecated("Use anotherMethod instead", ReplaceWith("anotherMethod(a, b, block)"))
fun <T : Int> aMethod(a: T, b: T = (a * 2) as T, block: () -> Unit) {}

fun main() {
    aMethod(1) { }
}
