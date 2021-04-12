// Original bug: KT-12451

fun main() {
    checkcast()
}

fun <T> ugly(value : Any?) = value as T
fun checkcast() {
    println('c' == ugly<Char>("c")) // java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Character
}
