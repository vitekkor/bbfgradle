// Original bug: KT-20994

interface IDemo {
    val Int.twice get() = this * 2
}

open class Demo {
    val Int.twice get() = this * 2
}

//class MyDemo : Demo() { // WORKS
class MyDemo : IDemo { // FAILS
    fun yay() {
        println(10.twice)
    }
}

fun main(args: Array<String>) {
    val demo = MyDemo()
    demo.yay()
}
