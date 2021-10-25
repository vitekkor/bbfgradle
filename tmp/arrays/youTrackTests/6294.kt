// Original bug: KT-15897

fun <T> verySafeCast(x: Any?) = x as? T

fun main(args: Array<String>) {
    val x = verySafeCast<String>(Any())
    if (x != null) {
        println(x.length)
    }
}
