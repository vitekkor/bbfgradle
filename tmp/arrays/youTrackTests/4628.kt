// Original bug: KT-35790

fun main() {
    println("Max from function as expression: ${maxAsExpression(42, 81)}") // breakpoint 
} 

fun maxAsExpression(a: Int, b: Int): Int = if (a > b) a else b
