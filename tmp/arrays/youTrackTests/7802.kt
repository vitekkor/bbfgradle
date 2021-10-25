// Original bug: KT-25309

open class A {
    open fun f(x: Int, y: Int , z: Int = 3) {
    }
}

interface I {
    fun f(x: Int, y: Int = 2, z: Int)
}

open class B : A(), I {
    override fun f(x: Int, y: Int, z: Int) {
        println("$x $y $z")
    }
}

fun main(args: Array<String>) {
    B().f(4) // prints 4 0 3
}
