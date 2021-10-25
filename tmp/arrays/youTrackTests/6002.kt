// Original bug: KT-25543

interface I {
    companion object {
        init { println("Init interface companion") }
        fun accessInterfaceCompanion() = println("Accessed interface companion")
    }

    fun interfaceMethod() = println("Interface method")
}

class C: I {
    companion object {
        init { println("Init class companion") }
    }

    init { println("Init class instance") }
    fun classMethod() = println("Class method")
}

fun main(args: Array<String>) {
    val c = C()
    c.classMethod()
    c.interfaceMethod()
    I.accessInterfaceCompanion()
}
