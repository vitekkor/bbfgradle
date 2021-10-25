// Original bug: KT-44483

fun <K, V> f(vararg p: Pair<K, V>): K = p[0].first

fun box(): String {
    val f: (Array<out Pair<String, Int>>) -> String = ::f
    return f(arrayOf("OK" to 0))
}
