// Original bug: KT-9828

package test

import java.util.*

fun main(args: Array<String>) {
    val hashMap = HashMap<String, Int>()
    hashMap.put("one", 1)
    hashMap.put("two", 2)
    for ((key, value) in hashMap) {
    }
}

