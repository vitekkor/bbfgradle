// Original bug: KT-17958

class Tmp(xx: List<Int>) {
    val xx: List<Int> = xx.sorted()
    fun m1(): List<Int> = xx
    val m2: List<Int> by lazy { xx }
}
fun main(args: Array<String>) {
    val t = Tmp(listOf<Int>(1, 9, 3, 7))
    println(t.m1())  //[1, 3, 7, 9]
    println(t.m2)    //[1, 9, 3, 7]
}
