// Original bug: KT-7450

inline fun fizz(x: Int, fn: ((Int, Int)->Int)->Int): Int =
        x * fn { y, z -> y * z }
 
fun test(x: Int): Int =
        fizz(x) { it(2, 2) }
 
fun main(args: Array<String>) {
    val result = test(3)
    println(result)
}
