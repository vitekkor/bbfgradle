// Original bug: KT-10036

class OverloadTest {
  fun foo(bar: Boolean) {}
  fun foo(bar: Any?) {}
}

object Literal

inline fun <T : Any> OverloadTest.overload(value: T?, function: OverloadTest.(T) -> Unit) {
  if (value == null) foo(Literal) else function(value)
}

// Overload resolution ambiguity
fun OverloadTest.overloadBoolean(value: Boolean?) = overload(value, OverloadTest::foo)

// Works fine
fun OverloadTest.overloadBoolean2(value: Boolean?) = overload(value) { foo(it) }
