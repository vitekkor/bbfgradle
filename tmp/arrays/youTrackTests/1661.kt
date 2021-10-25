// Original bug: KT-16752

fun foo(x: Int) = x + 1

class Incrementer : 
        (Int) -> Int by ::foo

fun main() {
    val incrementer = Incrementer()
    println(incrementer(5))
}
