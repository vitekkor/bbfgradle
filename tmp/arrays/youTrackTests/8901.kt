// Original bug: KT-17958

class Tmp(xx: List<Int>) {
    val xx: List<Int> = xx.sorted()
    fun m1(): Int {
        println("xx $xx")
        return xx.sum()
    }
    fun m2(): Int {
        println("xx $xx")
        return xx.sum()
    }
}
