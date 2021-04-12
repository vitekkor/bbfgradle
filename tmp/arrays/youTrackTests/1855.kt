// Original bug: KT-26694

interface Base {
    fun foo()
}

class Der1 : Base {
    override fun foo() {
        println("Der1")
    }
}

class Der2 : Base {
    override fun foo() {
        println("Der2")
    }
}

class Der(var der: Base) : Base by der

fun main(args: Array<String>) {
    val a = Der(Der1())
    a.foo()
    a.der = Der2()
    a.foo()
}
