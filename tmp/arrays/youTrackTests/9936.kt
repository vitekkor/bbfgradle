// Original bug: KT-1876

class A(val f: Int.(i:Int) -> Int) {
    fun foo() = 1.f(3)

    val <T> T.h: Int.(r: String) -> String get() = null!!

    fun <T> T.test2() {
        22.h("e") //error

        22.(h)("r")
    }

    val Int.q : String.() -> Unit get() = null!!

    fun Int.test3() {
        "t".q() //error
    }

    fun String.test4() {
        34.q(this)
        (3.q)() //error
    }
}

val String.foo: String.() -> Unit  get() = null!!

fun test() {
    "y".foo("") //error
}
fun main(args : Array<String>) {
    1.(fun Int.(s: String) = println(s))("!")
}
