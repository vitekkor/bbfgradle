// Original bug: KT-6144

abstract class A {
    abstract val foo: Int
    init { 
        assert(foo > 0) 
    }
}

class B(val bar: String) : A() {
	override val foo: Int
		get() = bar.length * 2
}

fun main(args: Array<String>) {
	B("")
}
