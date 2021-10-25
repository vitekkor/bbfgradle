// Original bug: KT-8005

public fun main(args: Array<String>) {
   testInline('a')
}

private fun testInline(foo: Char) {
	someInline { it == foo }
}

private inline fun someInline(predicate: (char: Char)->Boolean) {
	var i = 0
	val str = "abc"
	while (predicate(str[i]) && i < 3) {
		i++
	}
	println(i) // Actual 3, expected 1
}
