// Original bug: KT-34961

package test

fun main() {
    val countMap = CountMap<Int>()
    assert(countMap[42] == 0)
    println("getter successful")
    
    countMap[42]++
    assert(countMap[42] == 1)
    println("setter successful")
}

class CountMap<K>(val delegate: MutableMap<K, Int> = mutableMapOf()): MutableMap<K, Int> by delegate {
    override fun get(key: K): Int = delegate.getOrDefault(key, 0)
}
