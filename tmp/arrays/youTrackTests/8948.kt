// Original bug: KT-17540

private var _value: String = "old"
fun overrideValueAndReturnOld(newValue: String) = _value.also { _value = newValue }

fun main(args: Array<String>) {
	println(overrideValueAndReturnOld("new"))
}
