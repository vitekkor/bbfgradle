// Original bug: KT-35485

fun main() {
	println(listOf("", "123", "123", "546").mycount("123")) //2
}

fun <T> Iterable<T>.mycount(target: T): Int {
	return this.count { // the function from Collections.kt was invoked as expected
		it == target
	}
}

