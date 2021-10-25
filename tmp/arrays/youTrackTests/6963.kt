// Original bug: KT-22448

class Bar {
	val value = "value"
}

object Foo {
	val bar: Bar = factory()
	
	private fun factory(): Bar {
		return bar
	}
}

fun main(args: Array<String>) {
	println(Foo.bar.value)
}
