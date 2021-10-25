// Original bug: KT-21354

class C() {
    var arr = intArrayOf(1, 2, 3)

    fun updateArr() {
        arr = intArrayOf()
    }
}

fun main(args: Array<String>) {
    println("C.arr:")
    val c = C()
    for (x in c.arr) {
        println(x)
        c.updateArr()
    }
}
