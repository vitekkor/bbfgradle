// Original bug: KT-17364

class A {
    companion object foo {
        fun bar() {
            println("bar in companion")
        }
        override fun toString(): String {
            return "companion"
        }
        val foo = object {
            fun bar() {
                println("bar in object in companion")
            }
            override fun toString(): String {
                return "object in companion"
            }
        }
    }
}

fun main(args: Array<String>) {
    println(A.foo) // is it property of companion or companion itself?
    A.foo.bar() // is it method of companion or method of companion property?
}
