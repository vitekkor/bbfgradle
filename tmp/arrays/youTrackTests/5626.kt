// Original bug: KT-33275

fun main() {
    A().lazyProp
}

class A {
    val lazyProp by lazy {
        runner {
            println("Once printed") // Breakpoint here
            println("This line too")
        }
    }

    fun runner(foo: () -> Unit) {
        println("A.runner")
        foo()
    }
}
