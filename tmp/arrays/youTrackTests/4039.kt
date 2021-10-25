// Original bug: KT-23141

class Expression<T>

infix fun<T, S1: T?, S2: T?> Expression<in S1>.neq(other: Expression<in S2>) {}

infix fun <T> Expression<T>.neq(other: T) {}

fun main(args: Array<String>) {
   Expression<Int?>() neq null // <-- Cannot resolve here 
}
