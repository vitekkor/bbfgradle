// Original bug: KT-18947

private interface I {
	fun method()
}

private class A : I {
	override fun method() {
		println("test")
	}
}

private inline fun test(obj: I, function: (I) -> Unit) {
	function(obj)
}

fun main(args: Array<String>) {
	test(A(), I::method)
}
