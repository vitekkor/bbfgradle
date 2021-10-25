// Original bug: KT-23149

class Test<K, V>(map: MutableMap<K, V>): MutableMap<K, V> by map

fun main(args: Array<String>) {
    val test = Test(mutableMapOf(1 to "hello"))
    test[1] += " there"
}
