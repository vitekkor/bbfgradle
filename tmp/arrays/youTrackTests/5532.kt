// Original bug: KT-25184

class MyClass1(int: Int) {
	private var i: Int = int
}

class MyClass2(private var int: Int)

class MyClass3 {
	private var i: Int?

	constructor() {
		i = null
	}

	constructor(int: Int) {
		i = int
	}
}
