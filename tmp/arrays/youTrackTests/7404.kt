// Original bug: KT-27559

fun main() {
    var numbers = (1..100).asSequence()
    
    var divisor = 2
    while (divisor < 100) {
        numbers = numbers.filter { it <= divisor || it % divisor != 0 }
        divisor++
    }
    println(numbers.toList())
}
