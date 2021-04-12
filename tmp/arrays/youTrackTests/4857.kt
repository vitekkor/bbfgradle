// Original bug: KT-18130

open class A
class B : A() {
    fun foo(){}
}
fun bar(s: Any) {}

fun main(args: Array<String>) {

    val b = B()
    var a: A = b

    if (a is B) {
        bar(if (true) {a = A()} else Unit)
        a.foo()
    }
}
