// Original bug: KT-32965

fun main() {
    MyEnum.B.foo()
}

enum class MyEnum {
    A {
        override fun foo() {
            println("A")
        }
    },
    B {
        override fun foo() {
            println("B")
            A.foo() // Redundant qualifier name
        }
    };

    abstract fun foo()
}
