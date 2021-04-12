// Original bug: KT-34813

class Foo(val bar:Int?)

fun <T>T.capture() = listOf(this)

// infered type: List<Int?>
fun r1(foo :Foo?) = if (foo == null) 10.capture() else foo.bar.capture()

// infered type: List<Int>
fun r2(foo :Foo?) = foo?.bar?.capture() ?: 10.capture()
