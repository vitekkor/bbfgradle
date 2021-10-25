// Original bug: KT-3118

package testing

class Test {
    private val hello: String
        get() { return "hello" }

    fun sayHello() = println(hello)
}

fun main(args: Array<String>) {
    Test().sayHello()
}
