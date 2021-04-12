// Original bug: KT-8120

fun foo(v: Int) {
    data class Test(val x: Int) {
        fun get1() = Test(v)
        fun get2() = Test(v)
        fun get3() = Test(v)
    }
    println(Test(3).get3())
    println(Test(2).get2())
    println(Test(1).get1())
}

fun main() {
    foo(0)
}
