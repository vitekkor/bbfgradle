// Original bug: KT-8761

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

fun <K, V> ConcurrentMap<K, V>.forEachValue(action: (V) -> Unit) {}

fun main() {
    val map = ConcurrentHashMap<String, Int>()
    map.forEachValue { v ->
        // expected v to be `Int` here, got `Int!`
    }
}

