// Original bug: KT-38164

interface IA {
    override fun toString(): String
}

open class C {
    override fun toString(): String {
        return "C"
    }
}

class A(val i: IA) : C(), IA by i

fun main() {
    println(A(object : IA {
        override fun toString() = "IA"
    }).toString()) // prints "IA"
}
