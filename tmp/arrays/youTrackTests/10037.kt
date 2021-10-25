// Original bug: KT-7617

public fun main(args: Array<String>) {
	Foo().bar() // Error
}

open class SuperFoo {
	public fun bar() {
		if (this is Foo) {
			superFoo() // Smart cast
			baz() // Cannot be cast
		}
	}

	public fun baz() {
	}
}

class Foo : SuperFoo() {
	public fun superFoo() {}
}
