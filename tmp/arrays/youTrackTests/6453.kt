// Original bug: KT-28455

package test

operator fun <T> Array<out T>.set(i: Int, value: T) {
    println("Array<out T>.set: i=$i, value=$value")
}

fun test(a: Array<out String>) {
    a[0] = "abc" // (*)
}

fun main(args: Array<String>) {
    test(arrayOf("0", "1", "2"))
}
