// Original bug: KT-12964

class A1 {
    fun <T> a1(t: T): Unit {}
    fun test1(): (String) -> Unit = A1()::a1  // error
}

class A2 {
    fun <K, V> a2(key: K): V = null!!
    fun test2(): (String) -> Unit = A2()::a2  // error
    fun <T3> test3(): (T3) -> T3 = A2()::a2   // error
}
