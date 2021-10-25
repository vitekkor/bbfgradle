// Original bug: KT-5685

fun main(args: Array<String>)
{
	Measurements()
}

class Measurements
{
	inline fun measure(key: String, logEvery: Long = -1, divisor: Int = 1, body: () -> Unit) {}
}
