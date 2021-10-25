// Original bug: KT-3883

fun f(cond: Boolean) {
	val bar: Any
	do {
		if (cond) {bar = "value"; break}
	} while (true)
	println(bar) // error "must be initialized"

	val foo: Any
	while (true) {
		if (cond) {foo = "value"; break}
	}
	println(foo) // works
}
