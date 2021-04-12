// Original bug: KT-24176

import java.util.*

data class SomeBean(val foo:String, val bar:String)

fun <T> assertEquals(value:T,expected:T):T {
	if (!Objects.equals(value, expected)) {
		throw AssertionError()
	}
	return value;
}

fun main(args: Array<String>) {

	with(SomeBean("foo", "bar")) {
		assertEquals(foo, "foo")
		assertEquals(bar, "bar")
	}

}
