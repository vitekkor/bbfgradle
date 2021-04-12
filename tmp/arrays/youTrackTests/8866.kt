// Original bug: KT-18208

fun main(args: Array<String>) {
	A().run("test")
}


data class A(val runA: A.(String) -> Unit = {}) {
	fun run(a: String) = runA(a) // Workaround for KT-18137
}
