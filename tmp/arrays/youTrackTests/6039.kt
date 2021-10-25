// Original bug: KT-31882

class Foo(val bar: Bar?)
class Bar(val prop: String)

fun f(foo: Foo) {
	val x = if (foo.bar?.prop?.startsWith(".") == true) foo.bar.prop else null
	println(x)
	val y = if (foo.bar?.prop?.startsWith(".") ?: false) foo.bar!!.prop else null
	println(y)
}
