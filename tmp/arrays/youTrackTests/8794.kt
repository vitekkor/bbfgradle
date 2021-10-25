// Original bug: KT-18689

var result: String = "Fail"

class Foo {
    fun foo() { result = "OK" }
}

class Test {
    private val foo = Foo()
    private val actions = ArrayList<() -> Unit>()

    fun create() {
        inlineFn(foo::foo) // bound member ref is necessary
    }

    fun test() {
        for (action in actions) {
            action()
        }
    }

    private inline fun inlineFn(
            crossinline fn: () -> Unit,
            x: Int? = null // if this default argument is removed, everything works ok
    ) {
        actions.add { fn() }
    }
}

fun main(args: Array<String>) {
    Test().run {
        create()
        test()
    }
    println(result)
}
