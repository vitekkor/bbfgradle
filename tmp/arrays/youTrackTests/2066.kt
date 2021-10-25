// Original bug: KT-35467

fun main() {
	// extracts incorrectly - returns Unit, but String expected
	val a = getA() // Unit
	val c = getC() // Unit
	val d = getD() // Unit
	val e = getE() // Unit

	// extracts correctly for all these copied extensions
	val e2 = s() // String

}

private fun getE() {
	"".apply { this }
}

private fun getD() {
	"".run { this }
}

private fun getA() {
	"".takeIf { true }
}

private fun s() = "".apply2 { this }

private fun getC() {
	"".let { it }
}

inline fun <T> T.apply2(block: T.() -> Unit): T {
	block()
	return this
}
