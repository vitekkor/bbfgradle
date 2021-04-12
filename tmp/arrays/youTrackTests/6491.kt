// Original bug: KT-28878

package sample
inline class Foo(val v: Int) {
    val ref get() = ::v
}
fun main(args: Array<String>) {
    var a = Foo(5)
    val b = a.ref
    println("A: ${a.v} - ${b.get()}")
}
