// Original bug: KT-9924

abstract class A<T> {
	abstract fun test(a: T, b:Boolean = false)
}

class B : A<String>() {
	override fun test(a: String, b: Boolean) {}
}

fun main(args: Array<String>) {
	B().test("a")
}
