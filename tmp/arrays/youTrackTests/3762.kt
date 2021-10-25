// Original bug: KT-15690

class FunRefTest() {
    fun someFun() {}
}

fun test(ref: () -> Unit) {
    println(ref::class.java)
}

fun main(args: Array<String>) {
    val test = FunRefTest()
    test(test::someFun)
    test(test::someFun)
}
