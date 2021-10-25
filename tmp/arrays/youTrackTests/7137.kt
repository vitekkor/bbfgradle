// Original bug: KT-17224

fun main(args: Array<String>) { 
    val a: Int = 10000
    val anotherA: Int = a
    println(a === anotherA) // Prints 'true'
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    println(boxedA === anotherBoxedA) // !!!Prints 'false'!!!
}
