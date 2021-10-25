// Original bug: KT-9342

sealed class Outer {
    class Inner(val a: Int) : Outer()
    fun test() : Inner {
        return when(this) {
            is Inner -> Inner(a)
        }
    }
}
fun main(args: Array<String>) {
    println(Outer.Inner(5))
}
