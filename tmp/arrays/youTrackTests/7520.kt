// Original bug: KT-26799

fun main(args: Array<String>) {
    mapOf<Int, Int>()  // 1) Decompiled stub, WRONG
    mapOf(1 to 1)      // 2) fun <K, V> mapOf(pair: Pair<K, V>) in MapsJVM.kt - OK
    mapOf(             // 3) same as above, WRONG, tracked in KT-26004
        "int" to "",
        "long" to ""
    )
}
