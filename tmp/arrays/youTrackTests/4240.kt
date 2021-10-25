// Original bug: KT-37261

fun foo(bar: String?) {
	println(bar == null) // ok
	println(bar != null) // ok
	println(bar === null) // suggest changing to "bar == null"
	println(bar !== null) // suggest changing to "bar != null"
}
