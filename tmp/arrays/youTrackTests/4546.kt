// Original bug: KT-31883

class Foo {
	fun f() :Boolean = Math.random() < 0.5
}

fun repro(foo: Foo?) {
	if (foo?.f() ?: false) println(foo!!.f()) else println("null")
	if (foo?.f() == true) println(foo!!.f()) else println("null")
}
