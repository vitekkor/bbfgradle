// Original bug: KT-21218



class bar (
    val x : String = "glorp"
) {

    val y : String by lazy { x + "y" }

    fun foo() {
        println("hi, x is $x, y is $y")
    }
}

fun main(args: Array<String>) {
    bar().foo()
}
