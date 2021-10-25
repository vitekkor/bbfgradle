// Original bug: KT-40879

class Test(private val foo: String) {
	fun foo() = Inner().foo()

	private inner class Inner { // Redundant 'inner' modifier
		fun foo() = Inner2().foo()
	}

	private inner class Inner2() {
		fun foo() = foo
	}
}

fun main() {
	Test("foo").foo()
}
