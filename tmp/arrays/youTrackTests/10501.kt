// Original bug: KT-5032

open class C(val s: String) {
    fun test(): String {
        return s
    }
}

class B(var x: Int) {
    fun foo(): String {
        var s = "OK"
        class Z : C(s) {}
        return Z().test()
    }
}


fun box() : String {
    val b = B(1)
    val result = b.foo()
    return result
}
fun main(args: Array<String>) {
  box()
}
