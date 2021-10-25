// Original bug: KT-14660

// kotlinc e024.kt -d Solution.jar && kotlin -cp Solution.jar Solution

/*
 * A permutation is an ordered arrangement of objects. For example, 3124
 * is one possible permutation of the digits 1, 2, 3 and 4. If all of
 * the permutations are listed numerically or alphabetically, we call it
 * lexicographic order. The lexicographic permutations of 0, 1 and 2 are:
 *
 * 012   021   102   120   201   210
 *
 * What is the millionth lexicographic permutation of the digits 0, 1, 2,
 * 3, 4, 5, 6, 7, 8 and 9?
 */
@file:JvmName("Solution")

fun fib(callback : (Int) -> Boolean) {
    var x = 0;
    var y = 1;
    var shouldContinue = true
    while(shouldContinue) {
        shouldContinue = callback(x)
        val value = y + x
        x = y
        y = value
    }
}
fun nth(n: Int, generator: ((Int) -> Boolean) -> Unit): Int {
    var i = 0
    var result = 0
    generator { result = it; i++ < n }
    return result
}

fun main(args : Array<String>) {
    val n = if (args.size == 0) 10 else args[0].toInt()
    val result = nth(n, ::fib)
    println("F$n is $result")
}