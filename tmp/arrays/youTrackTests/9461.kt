// Original bug: KT-13252

var log = ""

class A(val value: Int) {
    operator fun plus(other: A): A {
        log += "A.plus(${other.value});"
        return A(value + other.value)
    }
}

val _array = arrayOf(A(2))

fun getArray(): Array<A> {
    log += "getArray();"
    return _array
}

fun main(args: Array<String>) {
    getArray()[0] += A(3)
    println("value: ${_array[0].value}")
    println("log: $log")
}
