// Original bug: KT-27614

inline class A(val t: Int)
suspend fun produce1() = A(10)
fun produce2() = A(10)
suspend fun main() {
    println(produce1() == produce1()) // ClassCastException
    println(produce1() == produce2()) // ClassCastException
    println(produce1() == A(10)) // ClassCastException
    println(produce2() == produce2()) // OK
    println(produce2() == A(10)) // OK
}
