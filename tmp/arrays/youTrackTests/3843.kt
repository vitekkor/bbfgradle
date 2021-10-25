// Original bug: KT-17364

interface B {
    fun bar()
}

class A {
    companion object foo : B {
        override fun bar() {
            println("bar in companion")
        }
        override fun toString(): String {
            return "companion"
        }
        val foo = object : B {
            override fun bar() {
                println("bar in object in companion")
            }
            override fun toString(): String {
                return "object in companion"
            }
        }
    }
}

fun main(args: Array<String>) {
    println(A.foo)
    A.foo.bar()
    (A.foo).bar()
}
